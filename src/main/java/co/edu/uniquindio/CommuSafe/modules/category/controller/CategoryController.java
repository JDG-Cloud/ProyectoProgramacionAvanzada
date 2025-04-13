package co.edu.uniquindio.CommuSafe.modules.category.controller;

import co.edu.uniquindio.CommuSafe.modules.category.ResourceNotFoundException;
import co.edu.uniquindio.CommuSafe.modules.category.dto.*;
import co.edu.uniquindio.CommuSafe.modules.category.service.CategoryService;
import co.edu.uniquindio.CommuSafe.modules.util.MessageAlertDTO;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
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
    public ResponseEntity<MessageAlertDTO<List<CategoryDTO>>> getCategoriesForDropdown(
            @RequestParam(defaultValue = "false") boolean forDropdown) {
        List<CategoryDTO> categories = categoryService.getCategories(forDropdown);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, categories));
    }

    /**
     * Obtener categoría por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MessageAlertDTO<CategoryDTO>> getCategoryById(
            @PathVariable @NotBlank(message = "El ID de la categoría no puede estar vacío") String id) {
        validateCategoryExists(id);
        CategoryDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, category));
    }

    /**
     * Crear nueva categoría
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<MessageAlertDTO<String>> createCategory(
            @Valid @RequestBody CreateCategoryRequestDTO categoryDTO) {
        validateCategoryNameNotDuplicate(categoryDTO.getName());
        CreateCategoryResponseDTO response = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageAlertDTO<>(false, "Categoría creada exitosamente"));
    }

    /**
     * Actualizar categoría existente
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<MessageAlertDTO<String>> updateCategory(
            @PathVariable @NotBlank(message = "El ID de la categoría no puede estar vacío") String id,
            @Valid @RequestBody UpdateCategoryDTO categoryDTO) {
        validateCategoryExists(id);
        // Validar que el nuevo nombre no esté duplicado con otra categoría
        validateCategoryNameNotDuplicateForUpdate(id, categoryDTO.getName());
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, "Categoría actualizada correctamente"));
    }

    /**
     * Eliminar categoría
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<MessageAlertDTO<String>> deleteCategory(
            @PathVariable @NotBlank(message = "El ID de la categoría no puede estar vacío") String id) {
        validateCategoryExists(id);
        validateCategoryNotInUse(id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, "La categoría ha sido eliminada exitosamente"));
    }

    // Métodos de validación

    private void validateCategoryExists(String id) {
        try {
            categoryService.getCategoryById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + id);
        }
    }

    private void validateCategoryNameNotDuplicate(String name) {
        // Implementar lógica para verificar si ya existe una categoría con el mismo nombre
        // Ejemplo:
        List<CategoryDTO> categories = categoryService.getAllCategories();
        boolean exists = categories.stream()
                .anyMatch(cat -> cat.getName().equalsIgnoreCase(name));
        if (exists) {
            throw new ValidationException("Ya existe una categoría con el nombre: " + name);
        }
    }

    private void validateCategoryNameNotDuplicateForUpdate(String id, String name) {
        // Similar a validateCategoryNameNotDuplicate, pero excluyendo la categoría actual
        // Ejemplo:
        List<CategoryDTO> categories = categoryService.getAllCategories();
        boolean exists = categories.stream()
                .filter(cat -> !cat.getId().equals(id)) // Excluir la categoría que estamos actualizando
                .anyMatch(cat -> cat.getName().equalsIgnoreCase(name));
        if (exists) {
            throw new ValidationException("Ya existe otra categoría con el nombre: " + name);
        }
    }

    private void validateCategoryNotInUse(String id) {
        // Implementar lógica para verificar si la categoría está siendo utilizada
        // por algún reporte u otra entidad antes de eliminarla
        // Esta lógica dependerá de tu modelo de datos y reglas de negocio
        // Ejemplo ficticio:
        /*
        long reportsUsingCategory = reportService.countReportsByCategory(id);
        if (reportsUsingCategory > 0) {
            throw new ValidationException("No se puede eliminar la categoría porque está siendo utilizada por "
                    + reportsUsingCategory + " reportes");
        }
        */
    }
}