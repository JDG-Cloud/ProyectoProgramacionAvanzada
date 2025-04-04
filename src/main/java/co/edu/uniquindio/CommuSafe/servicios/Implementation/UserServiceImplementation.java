package co.edu.uniquindio.CommuSafe.servicios.Implementation;

import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.CommuSafe.mapper.UserMapper;
import co.edu.uniquindio.CommuSafe.modelo.Rol;
import co.edu.uniquindio.CommuSafe.modelo.User;
import co.edu.uniquindio.CommuSafe.repositorios.UserRepo;
import co.edu.uniquindio.CommuSafe.servicios.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserMapper userMapper;
    private final UserRepo userDocument;

    @Override
    public void createUser(CrearUsuarioDTO createUserDTO) throws Exception{

        if( alreadyExitsEmail(createUserDTO.email()) ){
            throw new Exception("El correo "+createUserDTO.email()+" ya está en uso");
        }

        User newUser = userMapper.toDocument(createUserDTO);

       //Se guarda en la base de datos
        userDocument.save(newUser);
       //envio de correo con el codigo de activacion

    }
    private boolean alreadyExitsEmail(String email) {
        return userDocument.findByEmail(email).isPresent();
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
    public void editUser(EditarUsuarioDTO editUserDTO) {

        //Validamos el id
        if (!ObjectId.isValid(EditarUsuarioDTO.id())) {
            throw new Exception("No se encontró el usuario con el id "+EditarUsuarioDTO.id());
        }



    }

    @Override
    public void deleteUser(String deleteUserId) {
        return "";
    }





}
