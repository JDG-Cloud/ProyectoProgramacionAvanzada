package co.edu.uniquindio.CommuSafe.servicios;

import co.edu.uniquindio.CommuSafe.controladores.UsuarioControlador;
import co.edu.uniquindio.CommuSafe.dto.report.CreateReportDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.EditarUsuarioDTO;

public interface UsuarioServicio {

    String createUser(CrearUsuarioDTO createUserDTO);
    String editUser(EditarUsuarioDTO editUserDTO);
    String deleteUser(String deleteUserId);
    //String createReport(CreateReportDTO createReportDTO);
}
