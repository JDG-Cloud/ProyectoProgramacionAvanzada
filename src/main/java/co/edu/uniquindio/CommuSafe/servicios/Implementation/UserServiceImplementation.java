package co.edu.uniquindio.CommuSafe.servicios.Implementation;

import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.CommuSafe.modelo.Rol;
import co.edu.uniquindio.CommuSafe.modelo.User;
import co.edu.uniquindio.CommuSafe.repositorios.UserRepo;
import co.edu.uniquindio.CommuSafe.servicios.Interface.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImplementation implements UserService {
    public UserServiceImplementation(UserRepo usuario) {
        this.userDocument = userDocument;

    }
    private UserRepo userDocument;
    @Override
    public void createUser(CrearUsuarioDTO createUserDTO) throws Exception{

      if (alreadyExitsEmail(createUserDTO.email()))throw new Exception("Email already exist");

      User newUser = new User();
       newUser.setNombre(createUserDTO.nombre());
       newUser.setPassword(createUserDTO.password());
       newUser.setCorreo(createUserDTO.email());
      // newUser.set
       //DAtos internos de la base de datos
       newUser.setRol(Rol.CLIENTE);

       //Se guarda en la base de datos
        userDocument.save(newUser);
      //envio de correo con el codigo de activacion



    }
    private boolean alreadyExitsEmail(String email) {
        List<User> users = userDocument.findAll();
        for (User user : users) {

        }
        return false;
    }
    private String generateCodeRandom(){
        String code = "0123456789";
        StringBuilder codeText = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * code.length());
            codeText.append(code.charAt(index));
        }
        return code.toString();
    }

    @Override
    public void editUser(String idUser,EditarUsuarioDTO editUserDTO) {


    }

    @Override
    public String deleteUser(String deleteUserId) {
        return "";
    }

    @Override



}
