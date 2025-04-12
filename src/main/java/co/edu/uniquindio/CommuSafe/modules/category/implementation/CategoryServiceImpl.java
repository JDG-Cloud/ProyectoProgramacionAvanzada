package co.edu.uniquindio.CommuSafe.modules.category.implementation;

import co.edu.uniquindio.CommuSafe.exceptions.CustomException;
import co.edu.uniquindio.CommuSafe.modules.category.dto.*;
import co.edu.uniquindio.CommuSafe.modules.category.mapper.CategoryMapper;
import co.edu.uniquindio.CommuSafe.modules.category.model.Category;
import co.edu.uniquindio.CommuSafe.modules.category.repository.CategoryRepository;
import co.edu.uniquindio.CommuSafe.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

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
            return getAllCategories();
        }
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + id));
        return categoryMapper.toDTO(category);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Categoría no encontrada con nombre: " + name));
        return categoryMapper.toDTO(category);
    }

    @Override
    public CreateCategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.name())) {
            throw new CustomException(HttpStatus.CONFLICT, "Ya existe una categoría con el nombre: " + categoryDTO.name());
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        category.setCreatedAt(LocalDate.now());
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(String id, UpdateCategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + id));

        if (!category.getName().equals(categoryDTO.name()) &&
                categoryRepository.existsByName(categoryDTO.name())) {
            throw new CustomException(HttpStatus.CONFLICT, "Ya existe una categoría con el nombre: " + categoryDTO.name());
        }

        category.setName(categoryDTO.name());
        category.setIcon(categoryDTO.icon());
        category.setDescription(categoryDTO.description());

        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + id));
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Categoría no encontrada con nombre: " + name));
        categoryRepository.deleteCategoryByName(name);
    }
}
