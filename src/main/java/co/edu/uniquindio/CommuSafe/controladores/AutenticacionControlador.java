package co.edu.uniquindio.CommuSafe.controladores;

import co.edu.uniquindio.CommuSafe.dto.LoginDTO;
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

    @PostMapping("/login")
    public ResponseEntity <String>iniciarSesion(@RequestBody LoginDTO loginDTO) throws Exception{
        //servicio->logica de negocio
        if(loginDTO.password().length()<7){
             return ResponseEntity.status(400).body("La contraseña es menor a 7 caracterres");
        }
        return ResponseEntity.status(200).body("Inicio de sesión correcto");
    }
}
