package co.edu.uniquindio.CommuSafe.dto.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record EditarUsuarioDTO(

      @NotBlank @NotNull @Length(max=100) String nombre,
      @NotBlank  @Length(max=100) String ciudad,
      @NotBlank  @Length(max=100) String direccion,
      @NotBlank @Length(max=100) String telefono

) {
}

