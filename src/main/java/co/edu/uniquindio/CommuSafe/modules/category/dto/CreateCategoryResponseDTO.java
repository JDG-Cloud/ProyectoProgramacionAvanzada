package co.edu.uniquindio.CommuSafe.modules.category.dto;


public record CreateCategoryResponseDTO(
        String id,  // El ID generado por MongoDB
        String name,
        String icon,
        String description
) {}
