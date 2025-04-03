package co.edu.uniquindio.CommuSafe.modules.user.repository;

import co.edu.uniquindio.CommuSafe.modules.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
