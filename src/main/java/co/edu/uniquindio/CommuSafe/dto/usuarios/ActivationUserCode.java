package co.edu.uniquindio.CommuSafe.dto.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActivationUserCode(

      @NotBlank @NotNull @Email String email,
      @NotBlank @NotNull String code
) {
}
