package co.edu.uniquindio.CommuSafe.controladores;

import co.edu.uniquindio.CommuSafe.dto.admin.CategoryDTO;
import co.edu.uniquindio.CommuSafe.dto.report.CreateReportDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.UsuarioDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    @PostMapping("/categories/newCategory")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO createCategoryDTO) throws Exception{
        return ResponseEntity.status(200).body("Categoria registrada correctamente");
    }
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDTO updateCategoryDTO) throws Exception{
        return ResponseEntity.status(200).body("Categoria actualizada correctamente");
    }
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory( ) throws Exception{
        return ResponseEntity.status(200).body("Categoria eliminada correctamente");
    }
    public List<CreateReportDTO> listAllReports(){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not implemented yet");
    }





}
