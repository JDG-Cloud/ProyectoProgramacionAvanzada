package co.edu.uniquindio.CommuSafe.modules.security.repository;

import co.edu.uniquindio.CommuSafe.modules.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
