package co.edu.uniquindio.CommuSafe.controladores;

import co.edu.uniquindio.CommuSafe.dto.LoginDTO;
import co.edu.uniquindio.CommuSafe.dto.MessageDTO;
import co.edu.uniquindio.CommuSafe.dto.OlvidoContrasenaDTO;
import co.edu.uniquindio.CommuSafe.dto.RestablecerContrasenaDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.NewPasswordValidationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AutenticacionControlador {

    @PostMapping("/ingresar")
    public ResponseEntity <MessageDTO<String>>iniciarSesion(@Valid  @RequestBody LoginDTO loginDTO) throws Exception{
        //servicio->logica de negocio
        return ResponseEntity.status(200).body(new MessageDTO<>(false, "Inicio de sesion correcto"));
    }

    @PostMapping("/olvidocontrasena")
    public ResponseEntity <String>olvidarConrasena(@Valid  @RequestBody OlvidoContrasenaDTO olvidoContrasenaDTO) throws Exception{
        //servicio->logica de negocio
        return ResponseEntity.status(200).body("Email encontrado");
    }

    @PostMapping("/restablecercontrasena")
    public ResponseEntity <String>restablecerContrasena(@Valid  @RequestBody RestablecerContrasenaDTO loginDTO) throws Exception{
        //servicio->logica de negocio
        return ResponseEntity.status(200).body("Codigo correcto");
    }

    @PostMapping("/restablecercontrasenanueva")
    public ResponseEntity <String>restablecerContrasenaNueva(@Valid  @RequestBody NewPasswordValidationDTO newPasswordDTO) throws Exception{
        //servicio->logica de negocio
        return ResponseEntity.status(200).body("Nueva Contraseña registrada");
    }
}
