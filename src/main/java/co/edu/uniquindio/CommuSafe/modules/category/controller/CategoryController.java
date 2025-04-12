package co.edu.uniquindio.CommuSafe.modules.category.controller;

import co.edu.uniquindio.CommuSafe.modules.category.dto.*;
import co.edu.uniquindio.CommuSafe.modules.category.service.CategoryService;
import co.edu.uniquindio.CommuSafe.modules.util.MessageAlertDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Obtener todas las categorías
     */
    @GetMapping
    public ResponseEntity<MessageAlertDTO<List<CategoryDTO>>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(new MessageAlertDTO<>(false, categories));
    }

    /**
     * Obtener categorías para dropdown (solo nombre + id)
     */
    @GetMapping("/dropdown")
    public ResponseEntity<MessageAlertDTO<List<CategoryDTO>>> getCategoriesForDropdown(@RequestParam(defaultValue = "false") boolean forDropdown) {
        List<CategoryDTO> categories = categoryService.getCategories(forDropdown);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, categories));
    }

    /**
     * Obtener categoría por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MessageAlertDTO<CategoryDTO>> getCategoryById(@PathVariable String id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, category));
    }

    /**
     * Crear nueva categoría
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<MessageAlertDTO<String>> createCategory(@Valid @RequestBody CreateCategoryRequestDTO categoryDTO) {
        CreateCategoryResponseDTO response = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, "Categoría creada exitosamente"));
    }

    /**
     * Actualizar categoría existente
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<MessageAlertDTO<String>> updateCategory(@PathVariable String id,
                                                                  @Valid @RequestBody UpdateCategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, "Categoría actualizada correctamente"));
    }

    /**
     * Eliminar categoría
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<MessageAlertDTO<String>> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, "La categoría ha sido eliminada exitosamente"));
    }
}