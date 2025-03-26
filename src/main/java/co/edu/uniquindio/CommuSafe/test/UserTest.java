package co.edu.uniquindio.CommuSafe.test;
import co.edu.uniquindio.CommuSafe.repositorios.UserRepo;
import co.edu.uniquindio.CommuSafe.test.repositorios.UserRepo;
import org.junit.jupiter.api.Test;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserTest {
    @Autowired
    private UserRepo userRepo;




}
