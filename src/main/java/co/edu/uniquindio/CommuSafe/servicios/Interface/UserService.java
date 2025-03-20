package co.edu.uniquindio.CommuSafe.servicios.Interface;

import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.EditarUsuarioDTO;
import org.springframework.stereotype.Service;


public interface UserService {
//tiene los mismos metodos de la api
    String createUser(CrearUsuarioDTO createUserDTO);
    String editUser(EditarUsuarioDTO editUserDTO);
    String deleteUser(String deleteUserId);

    //String createReport(CreateReportDTO createReportDTO);
}
