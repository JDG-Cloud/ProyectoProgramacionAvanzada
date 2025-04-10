package co.edu.uniquindio.CommuSafe.modules.category.implementation;

import co.edu.uniquindio.CommuSafe.modules.category.DuplicateResourceException;
import co.edu.uniquindio.CommuSafe.modules.category.ResourceNotFoundException;
import co.edu.uniquindio.CommuSafe.modules.category.dto.*;
import co.edu.uniquindio.CommuSafe.modules.category.mapper.CategoryMapper;
import co.edu.uniquindio.CommuSafe.modules.category.model.Category;
import co.edu.uniquindio.CommuSafe.modules.category.repository.CategoryRepository;
import co.edu.uniquindio.CommuSafe.modules.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getCategories(boolean forDropdown) {
        if (forDropdown) {
            return categoryRepository.findAllByOrderByNameAsc()
                    .stream()
                    .map(categoryMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return getAllCategories();  // Llama a getAllCategories si no es para dropdown
        }
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
        return categoryMapper.toDTO(category);
    }

    @Override
    public CreateCategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) { // Uso de getName() en lugar de name()
            throw new DuplicateResourceException("Ya existe una categoría con el nombre: " + categoryDTO.getName());
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        category.setCreatedAt(LocalDate.now()); // Corrección del setter
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(String id, UpdateCategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));

        if (!category.getName().equals(categoryDTO.name()) && // Uso de getName()
                categoryRepository.existsByName(categoryDTO.name())) {
            throw new DuplicateResourceException("Ya existe una categoría con el nombre: " + categoryDTO.name());
        }

        category.setName(categoryDTO.name());  // Uso de getName()
        category.setIcon(categoryDTO.icon());  // Uso de getIcon()
        category.setDescription(categoryDTO.description());  // Uso de getDescription()

        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
        categoryRepository.deleteById(id);  // Cambio: Se usa 'deleteById' en lugar de eliminar por nombre
    }
}
