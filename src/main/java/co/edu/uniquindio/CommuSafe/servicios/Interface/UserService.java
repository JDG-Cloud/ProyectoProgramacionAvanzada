package co.edu.uniquindio.CommuSafe.servicios.Interface;

import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.UsuarioDTO;
import co.edu.uniquindio.CommuSafe.modelo.User;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


public interface UserService  {
//tiene los mismos metodos de la api
    void createUser(CrearUsuarioDTO createUserDTO) throws Exception;
    void editUser(EditarUsuarioDTO editUserDTO);
    void deleteUser(String deleteUserId);
    UsuarioDTO getUser(String id);

}
