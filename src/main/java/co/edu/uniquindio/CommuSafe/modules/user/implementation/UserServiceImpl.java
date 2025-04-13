package co.edu.uniquindio.CommuSafe.modules.user.implementation;

import co.edu.uniquindio.CommuSafe.modules.security.security.JwtService;
import co.edu.uniquindio.CommuSafe.exceptions.CustomException;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationResponse;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationResponse;
import co.edu.uniquindio.CommuSafe.modules.user.service.UserService;
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


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtUtil;

    @Override
    public UserCreationResponse create(UserCreationRequest userCreationRequest) {
        try {
            // validar el cuerpo de la petición
            if (userCreationRequest == null || userCreationRequest.getEmail() == null) {
                throw new CustomException(HttpStatus.BAD_REQUEST,"invalid request");
            }

            Query query = new Query(Criteria.where("email").is(userCreationRequest.getEmail()));

            // Verificar si el usuario ya existe en la base de datos
            boolean exists = mongoTemplate.exists(query, User.class);
            if (exists) {
                throw new CustomException(HttpStatus.CONFLICT,"user already exists");
            }

            // Crear un nuevo usuario si no existe
            User newUser = new User();
            newUser.setName(userCreationRequest.getName());
            newUser.setEmail(userCreationRequest.getEmail());
            newUser.setAddress(userCreationRequest.getAddress());
            newUser.setPhone(userCreationRequest.getPhone());
            newUser.setRole(userCreationRequest.getRole());

            if (userCreationRequest.getPassword() != null && !userCreationRequest.getPassword().isEmpty()) {
                newUser.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
            }

            // Insertar el usuario en la base de datos
            mongoTemplate.insert(newUser);

            String token = jwtUtil.generateToken(newUser);
            if(token != null && !token.isEmpty()) {
                return new UserCreationResponse(token);
            }
            throw  new CustomException(HttpStatus.BAD_REQUEST,"user creation failed");
        } catch (DataAccessException e1) {
            throw  new CustomException(HttpStatus.BAD_REQUEST,String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw  new CustomException(e2.getStatus(),e2.getMessage());
        } catch (Exception e3) {
            throw  new CustomException(HttpStatus.BAD_REQUEST,String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }


    @Override
    public UserModificationResponse update(UserModificationRequest userModificationRequest) {

        try {
            if (userModificationRequest == null) {
                throw  new  CustomException(HttpStatus.BAD_REQUEST,"invalid request");
            }

            Query query = new Query(Criteria.where("email").is(userModificationRequest.getEmail()));
            // Verificar si el usuario ya existe en la base de datos
            boolean exists = mongoTemplate.exists(query, User.class);

            if(!exists) {
                throw  new  CustomException(HttpStatus.NOT_FOUND,"user not exists");
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
            return new UserModificationResponse("OK","user updated successfully");
        } catch (DataAccessException e1) {
            throw  new CustomException(HttpStatus.BAD_REQUEST,String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw  new CustomException(e2.getStatus(),e2.getMessage());
        } catch (Exception e3) {
            throw  new CustomException(HttpStatus.BAD_REQUEST,String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }
}
