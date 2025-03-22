package co.edu.uniquindio.CommuSafe.dto.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record  UsuarioDTO(
        @NotNull @NotBlank @Length(max =100) String nombre,
        @NotNull  @NotBlank @Length (max =50) String email,
        @NotNull  @NotBlank @Length (max =100) String ciudad,
        @NotNull  @NotBlank @Length (max =100) String direccion,
        @NotNull @NotBlank @Length (min = 7, max =15) String password,
        @NotNull  @NotBlank @Length (max =10) String telefono,
        @NotNull  @NotBlank Rol rol
) {
}
