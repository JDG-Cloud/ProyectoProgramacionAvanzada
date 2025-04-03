package co.edu.uniquindio.CommuSafe.user.repository;

import co.edu.uniquindio.CommuSafe.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
