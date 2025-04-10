package co.edu.uniquindio.CommuSafe.modules.category.mapper;

import co.edu.uniquindio.CommuSafe.modules.category.dto.*;
import co.edu.uniquindio.CommuSafe.modules.category.model.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapperUtil.class)
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_At", expression = "java(java.time.LocalDate.now())")
    Category toEntity(CreateCategoryRequestDTO dto);

    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "icon", target = "icon")
    @Mapping(source = "description", target = "description")
    CreateCategoryResponseDTO toResponseDTO(Category category);

    List<CreateCategoryResponseDTO> toListResponseDTO(List<Category> list);

    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "icon", target = "icon")
    @Mapping(source = "description", target = "description")
    CategoryDTO toDTO(Category category);  // ← Agregado

    List<CategoryDTO> toDTOList(List<Category> categories);  // ← Opcional pero útil

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDTO(UpdateCategoryDTO dto, @MappingTarget Category category);
}

