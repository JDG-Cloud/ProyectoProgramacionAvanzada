package co.edu.uniquindio.CommuSafe.modules.security.repository;

import co.edu.uniquindio.CommuSafe.modules.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface SecurityRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
