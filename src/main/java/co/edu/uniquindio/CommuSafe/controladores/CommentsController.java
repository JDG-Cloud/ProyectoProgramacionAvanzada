package co.edu.uniquindio.CommuSafe.controladores;


import co.edu.uniquindio.CommuSafe.dto.LoginDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

    @PostMapping("/ingresar")
    public ResponseEntity<String> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        //servicio->logica de negocio
        return ResponseEntity.status(200).body("Inicio de sesión correcto");
    }
}
