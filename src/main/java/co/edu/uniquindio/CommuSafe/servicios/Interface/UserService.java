package co.edu.uniquindio.CommuSafe.servicios.Interface;

import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.CommuSafe.modelo.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface UserService extends MongoRepository<User, ObjectId> {
//tiene los mismos metodos de la api
    String createUser(CrearUsuarioDTO createUserDTO) throws Exception;
    String editUser(EditarUsuarioDTO editUserDTO);
    String deleteUser(String deleteUserId);

    //String createReport(CreateReportDTO createReportDTO);
}
