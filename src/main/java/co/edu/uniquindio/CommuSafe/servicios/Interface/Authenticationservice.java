package co.edu.uniquindio.CommuSafe.servicios.Interface;

import co.edu.uniquindio.CommuSafe.dto.LoginDTO;
import co.edu.uniquindio.CommuSafe.dto.OlvidoContrasenaDTO;
import co.edu.uniquindio.CommuSafe.dto.RestablecerContrasenaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Authenticationservice {

    public ResponseEntity<String> iniciarSesion( LoginDTO loginDTO);

    public ResponseEntity <String>olvidarConrasena( OlvidoContrasenaDTO olvidoContrasenaDTO);

    public ResponseEntity <String>restablecerContrasena( RestablecerContrasenaDTO loginDTO);

    public ResponseEntity <String>restablecerContrasenaNueva( LoginDTO loginDTO);
}
