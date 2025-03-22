package co.edu.uniquindio.CommuSafe.controladores;

import co.edu.uniquindio.CommuSafe.dto.admin.CategoryDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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





}
