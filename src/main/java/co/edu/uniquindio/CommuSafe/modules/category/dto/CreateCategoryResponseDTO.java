package co.edu.uniquindio.CommuSafe.modules.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryResponseDTO {

    private String id;  // El ID generado por MongoDB
    private String name;
    private String icon;
    private String description;
}
