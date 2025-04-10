package co.edu.uniquindio.CommuSafe.modules.category.dto;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateCategoryDTO(

        @NotBlank(message = "El nombre no puede estar vacío")
        @Length(min = 3, max = 50)
        String name,

        @Length(min = 3, max = 150)
        String icon,

        @Length(min = 3, max = 300)
        String description
) {
}