package co.edu.uniquindio.CommuSafe.servicios.Interface;

import co.edu.uniquindio.CommuSafe.dto.LoginDTO;
import co.edu.uniquindio.CommuSafe.dto.OlvidoContrasenaDTO;
import co.edu.uniquindio.CommuSafe.dto.RestablecerContrasenaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Authenticationservice {

    @PostMapping("/ingresar")
    public ResponseEntity<String> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO);

    @PostMapping("/olvidocontrasena")
    public ResponseEntity <String>olvidarConrasena(@Valid  @RequestBody OlvidoContrasenaDTO olvidoContrasenaDTO);

    @PostMapping("/restablecercontrasena")
    public ResponseEntity <String>restablecerContrasena(@Valid  @RequestBody RestablecerContrasenaDTO loginDTO);
    @PostMapping("/restablecercontrasenanueva")
    public ResponseEntity <String>restablecerContrasenaNueva(@Valid  @RequestBody LoginDTO loginDTO);
}
