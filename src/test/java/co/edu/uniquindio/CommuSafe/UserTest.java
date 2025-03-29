package co.edu.uniquindio.CommuSafe;

import co.edu.uniquindio.CommuSafe.modelo.*;
import co.edu.uniquindio.CommuSafe.repositorios.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void registrarTest() {
        //Creamos un cliente con sus propiedades
        User user = User.builder()
                .nombre("Camilo")
                .ciudad("Armenia")
                .telefono("12345")
                .ubication(new Ubication(1.5, 1.0))
                .correo("pepito@email.com")
                .password("112121212")
                .rol(Rol.CLIENTE)
                .estadoUser(UserStatus.ACTIVO)
                .validationCode(new ValidationCode("1111", LocalDateTime.now()))
                .photoUser("aaaaaaa")
                .build();

        //Guardamos el usuario en la base de datos
        User userCreate = userRepo.save(user);


        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(userCreate);
    }

}
