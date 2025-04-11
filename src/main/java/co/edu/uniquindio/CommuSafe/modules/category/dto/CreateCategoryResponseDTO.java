package co.edu.uniquindio.CommuSafe.modules.category.dto;

import lombok.Data;

@Data
public class CreateCategoryResponseDTO {

    private String id;  // El ID generado por MongoDB
    private String name;
    private String icon;
    private String description;
}
