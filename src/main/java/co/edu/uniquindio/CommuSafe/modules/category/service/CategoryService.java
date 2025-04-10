package co.edu.uniquindio.CommuSafe.modules.category.service;

import co.edu.uniquindio.CommuSafe.modules.category.dto.*;

import java.util.List;

public interface CategoryService {
    /**
     * Obtiene todas las categorías del sistema.
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Obtiene todas las categorías para dropdowns (solo nombre + id).
     */
    List<CategoryDTO> getCategories(boolean forDropdown);

    /**
     * Obtiene una categoría por su ID.
     */
    CategoryDTO getCategoryById(String id);

    /**
     * Crea una nueva categoría.
     */
    CreateCategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryDTO);

    /**
     * Actualiza una categoría existente.
     */
    CategoryDTO updateCategory(String id, UpdateCategoryDTO categoryDTO);

    /**
     * Elimina una categoría por su ID.
     */
    void deleteCategory(String id);
}
