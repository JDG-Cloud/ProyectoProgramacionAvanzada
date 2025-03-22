package co.edu.uniquindio.CommuSafe.controladores;

import co.edu.uniquindio.CommuSafe.dto.LoginDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    @PostMapping("/{notificationId}/read")
    public ResponseEntity<String> notificationRead(@Valid @PathVariable String notificacionId) throws Exception{
        //servicio->logica de negocio
        return ResponseEntity.status(200).body("Inicio de sesión correcto");
    }

    @PutMapping("/{notificationId}/read-all")
    public ResponseEntity<String> notificationRead() throws Exception{
        //servicio->logica de negocio
        return ResponseEntity.status(200).body("Inicio de sesión correcto");
    }
}
