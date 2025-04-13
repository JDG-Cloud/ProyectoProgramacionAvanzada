package co.edu.uniquindio.CommuSafe.modules.category.dto;
import jakarta.validation.constraints.NotEmpty;

public record CreateCategoryRequestDTO(
        @NotEmpty(message = "El nombre de la categoría no puede estar vacío.")
        String name,

        @NotEmpty(message = "El ícono de la categoría no puede estar vacío.")
        String icon,

        String description
){}
