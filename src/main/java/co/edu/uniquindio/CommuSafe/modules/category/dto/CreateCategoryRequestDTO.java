package co.edu.uniquindio.CommuSafe.modules.category.dto;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateCategoryRequestDTO {

    @NotEmpty(message = "El nombre de la categoría no puede estar vacío.")
    private String name;

    @NotEmpty(message = "El ícono de la categoría no puede estar vacío.")
    private String icon;

    private String description;
}
