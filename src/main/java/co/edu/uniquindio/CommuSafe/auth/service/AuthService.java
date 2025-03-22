package co.edu.uniquindio.CommuSafe.auth.service;

import co.edu.uniquindio.CommuSafe.auth.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.auth.dto.AuthResponse;
import co.edu.uniquindio.CommuSafe.auth.model.User;
import co.edu.uniquindio.CommuSafe.auth.repository.UserRepository;
import co.edu.uniquindio.CommuSafe.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest authRequest) {
        Optional<User> user = userRepository.findByEmail(authRequest.getEmail());

        if (user.isPresent() && passwordEncoder.matches(authRequest.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(user.get().getEmail());
            return new AuthResponse(token);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect credentials");
        }
    }

    public void register(User user) {
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(user.getEmail()));

            Update update = new Update()
                    .set("name", user.getName())
                    .set("address", user.getAddress())
                    .set("phone", user.getPhone())
                    .set("password", passwordEncoder.encode(user.getPassword()))
                    .set("urlProfile", user.getUrlProfile())
                    .set("role", user.getRole());

            mongoTemplate.findAndModify(query, update,
                    org.springframework.data.mongodb.core.FindAndModifyOptions.options().returnNew(true).upsert(true),
                    User.class);
        }
    }
