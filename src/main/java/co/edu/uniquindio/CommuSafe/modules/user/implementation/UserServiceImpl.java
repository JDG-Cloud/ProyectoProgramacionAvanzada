package co.edu.uniquindio.CommuSafe.modules.user.implementation;

import co.edu.uniquindio.CommuSafe.CloudinaryConfig;
import co.edu.uniquindio.CommuSafe.modules.Email.ActivationDTO;
import co.edu.uniquindio.CommuSafe.modules.Email.EmailDTO;
import co.edu.uniquindio.CommuSafe.modules.security.model.Otp;
import co.edu.uniquindio.CommuSafe.modules.security.security.JwtService;
import co.edu.uniquindio.CommuSafe.exceptions.CustomException;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserResponse;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationResponse;
import co.edu.uniquindio.CommuSafe.modules.user.model.Role;
import co.edu.uniquindio.CommuSafe.modules.user.model.Status;
import co.edu.uniquindio.CommuSafe.modules.user.service.UserService;
import co.edu.uniquindio.CommuSafe.modules.util.EmailServicio;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import co.edu.uniquindio.CommuSafe.modules.user.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtUtil;
    @Autowired
    private CloudinaryConfig cloudinary;
    @Autowired
    private EmailServicio emailServicio;

    @Override
    public UserCreationResponse create(UserCreationRequest userCreationRequest) {
        try {
            // validar el cuerpo de la petición
            if (userCreationRequest == null || userCreationRequest.getEmail() == null) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "invalid request");
            }

            Query query = new Query(Criteria.where("email").is(userCreationRequest.getEmail()));

            // Verificar si el usuario ya existe en la base de datos
            boolean exists = mongoTemplate.exists(query, User.class);
            if (exists) {
                throw new CustomException(HttpStatus.CONFLICT, "user already exists");
            }

            // Crear un nuevo usuario si no existe
            User newUser = new User();
            newUser.setName(userCreationRequest.getName());
            newUser.setEmail(userCreationRequest.getEmail());
            newUser.setAddress(userCreationRequest.getAddress());
            newUser.setPhone(userCreationRequest.getPhone());
            newUser.setRole(Role.CLIENT);
            newUser.setStatus(Status.NoActive);

            if (userCreationRequest.getPassword() != null && !userCreationRequest.getPassword().isEmpty()) {
                newUser.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
            }

            String code = generarCodigo();
            newUser.setOtps( List.of(
                    new Otp(
                        code,
                        new Date()
                    )
            ) );

            // Insertar el usuario en la base de datos
            mongoTemplate.insert(newUser);

            emailServicio.enviarCorreo(new EmailDTO(
                    "Se ha registrado en la página de CommuSafe",
                    "Su código de validación para activar la cuenta es: "+code,
                    userCreationRequest.getEmail()
            ));

            String token = jwtUtil.generateToken(newUser);
            if (token != null && !token.isEmpty()) {
                return new UserCreationResponse(token);
            }
            throw new CustomException(HttpStatus.BAD_REQUEST, "user creation failed");
        } catch (DataAccessException e1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw new CustomException(e2.getStatus(), e2.getMessage());
        } catch (Exception e3) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }

    public String generarCodigo(){
        int random = (int) (Math.random() * 100000);
        return String.valueOf(random);
    }


    public void activate(ActivationDTO request) {
        try {
            if (request == null || request.email() == null || request.code() == null) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Invalid activation request");
            }

            Query query = new Query(Criteria.where("email").is(request.email()));
            User user = mongoTemplate.findOne(query, User.class);

            if (user == null) {
                throw new CustomException(HttpStatus.NOT_FOUND, "User not found");
            }

            Otp storedOtp = user.getOtps() != null && !user.getOtps().isEmpty()
                    ? user.getOtps().get(user.getOtps().size() - 1)
                    : null;

            if (storedOtp == null || !storedOtp.getCode().equals(request.code())) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Invalid or expired code");
            }

            //se valida que la duracion es de 15 minutos antes de expirar
            long otpValidityDuration = 15 * 60 ;
            Date currentTime = new Date();
            Date otpCreationTime = storedOtp.getCreatedAt();

            if (otpCreationTime == null ||
                    (currentTime.getTime() - otpCreationTime.getTime()) > otpValidityDuration) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "OTP has expired");
            }

            Update update = new Update().set("status", Status.Active);
            mongoTemplate.updateFirst(query, update, User.class);

        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Activation failed: " + e.getMessage());
        }
    }


    @Override
    public UserResponse update(UserModificationRequest userModificationRequest) {

        try {
            if (userModificationRequest == null) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "invalid request");
            }

            Query query = new Query(Criteria.where("email").is(userModificationRequest.getEmail()));
            // Verificar si el usuario ya existe en la base de datos
            boolean exists = mongoTemplate.exists(query, User.class);

            if (!exists) {
                throw new CustomException(HttpStatus.NOT_FOUND, "user not exists");
            }

            Update update = new Update()
                    .set("name", userModificationRequest.getName())
                    .set("address", userModificationRequest.getAddress())
                    .set("phone", userModificationRequest.getPhone())
                    .set("email", userModificationRequest.getEmail())
                    .set("role", userModificationRequest.getRole());

            if (userModificationRequest.getPassword() != null && !userModificationRequest.getPassword().isEmpty()) {
                update.set("password", passwordEncoder.encode(userModificationRequest.getPassword()));
            }

            mongoTemplate.findAndModify(
                    query, update,
                    FindAndModifyOptions.options().returnNew(true).upsert(true),
                    User.class
            );
            return new UserResponse("OK", "user updated successfully");
        } catch (DataAccessException e1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw new CustomException(e2.getStatus(), e2.getMessage());
        } catch (Exception e3) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }

    @Override
    public UserResponse delete(UserModificationRequest userModificationRequest) {
        try {
            if (userModificationRequest == null || userModificationRequest.getEmail() == null) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "invalid request");
            }

            // Buscar usuario por email
            Query query = new Query(Criteria.where("email").is(userModificationRequest.getEmail()));
            boolean exists = mongoTemplate.exists(query, User.class);

            if (!exists) {
                throw new CustomException(HttpStatus.NOT_FOUND, "user not exists");
            }

            // Eliminar usuario
            mongoTemplate.remove(query, User.class);

            return new UserResponse("OK", "user deleted successfully");

        } catch (DataAccessException e1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw new CustomException(e2.getStatus(), e2.getMessage());
        } catch (Exception e3) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }

    @Override
    public UserResponse uploadUserProfileImage(MultipartFile file, String userId) {
        try {
            if (file.isEmpty()) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Please select a file to upload");
            }

            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Only image files are allowed");
            }

            uploadImage(file, userId);

            return new UserResponse("OK", "user profile image uploaded successfully");

        } catch (IOException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("Failed to upload image: %s", e.getMessage()));
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("An unexpected error occurred: %s", e.getMessage()));
        }
    }

    public String uploadImage(MultipartFile file, String userId) throws IOException {

        Map<String, String> options = new HashMap<>();
        options.put("resource_type", "auto");
        options.put("folder", "profiles");

        Map uploadResult = cloudinary.cloudinary().uploader().upload(file.getBytes(), options);

        String imageUrl = (String) uploadResult.get("secure_url");

        updateUserProfileUrl(userId, imageUrl);

        return imageUrl;
    }

    private void updateUserProfileUrl(String userId, String profileUrl) {
        Query query = new Query(Criteria.where("id").is(userId));
        boolean exists = mongoTemplate.exists(query, User.class);

        if (!exists) {
            throw new CustomException(HttpStatus.NOT_FOUND, "user not exists");
        }

        Update update = new Update()
                .set("urlProfile", profileUrl);

        mongoTemplate.findAndModify(
                query, update,
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                User.class
        );
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            if (email == null || email.isEmpty()) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "email is required");
            }

            Query query = new Query(Criteria.where("email").is(email));
            User user = mongoTemplate.findOne(query, User.class);

            if (user == null) {
                throw new CustomException(HttpStatus.NOT_FOUND, "user not found");
            }

            return user;

        } catch (DataAccessException e1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw new CustomException(e2.getStatus(), e2.getMessage());
        } catch (Exception e3) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }

}
