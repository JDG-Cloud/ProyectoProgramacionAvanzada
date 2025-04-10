package co.edu.uniquindio.CommuSafe.modules.category.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateCategoryDTO (
    @NotBlank(message = "Debe registrar al menos un nombre")
    @Length(max = 50)
    String name,

    @NotBlank(message = "Debe registrar al menos un icono")
    @Length(max = 150)
    String icon,

    @NotBlank(message = "Debe registrar al menos una descripción")
    @Length(max = 300)
    String description
    ){
}