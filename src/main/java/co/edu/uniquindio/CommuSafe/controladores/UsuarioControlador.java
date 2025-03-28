package co.edu.uniquindio.CommuSafe.controladores;


import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.UsuarioDTO;
import co.edu.uniquindio.CommuSafe.servicios.Interface.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/User")
public class UsuarioControlador {

    private UserService usuarioServicio;
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO) throws Exception{
        usuarioServicio.createUser(crearUsuarioDTO);
        return ResponseEntity.status(200).body("Usuario registrado correctamente");
    }

    @PutMapping("/usuario/miperfil/{id}")
    public void editUser(@Valid @RequestBody EditarUsuarioDTO editarUsuarioDTO)throws Exception{
        usuarioServicio.editUser(editarUsuarioDTO);
    }

    public ResponseEntity<String> deleteUser(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO) throws Exception{
        usuarioServicio.createUser(crearUsuarioDTO);
        return ResponseEntity.status(200).body("Usuario registrado correctamente");
    }
@DeleteMapping("/miperfil/{id}")
    public UsuarioDTO getUser(@Valid @PathVariable String id) throws Exception{
        usuarioServicio.deleteUser(id);
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not implemented yet");
    }

}
