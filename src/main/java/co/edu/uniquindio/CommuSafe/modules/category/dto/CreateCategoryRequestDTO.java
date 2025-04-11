package co.edu.uniquindio.CommuSafe.modules.category.dto;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class CreateCategoryRequestDTO {

    @NotEmpty(message = "El nombre de la categoría no puede estar vacío.")
    private String name;

    @NotEmpty(message = "El ícono de la categoría no puede estar vacío.")
    private String icon;

    private String description;
}
