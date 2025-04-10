package co.edu.uniquindio.CommuSafe.modules.category.controller;

import co.edu.uniquindio.CommuSafe.modules.category.dto.*;
import co.edu.uniquindio.CommuSafe.modules.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Obtener todas las categorías
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * Obtener categorías para dropdown (solo nombre + id)
     */
    @GetMapping("/dropdown")
    public ResponseEntity<List<CategoryDTO>> getCategoriesForDropdown(@RequestParam(defaultValue = "false") boolean forDropdown) {
        // Cambiado el nombre del método a uno unificado
        return ResponseEntity.ok(categoryService.getCategories(forDropdown));
    }

    /**
     * Obtener categoría por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    /**
     * Crear nueva categoría
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<CreateCategoryResponseDTO> createCategory(@Valid @RequestBody CreateCategoryRequestDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    /**
     * Actualizar categoría existente
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String id,
                                                      @Valid @RequestBody UpdateCategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    /**
     * Eliminar categoría
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
