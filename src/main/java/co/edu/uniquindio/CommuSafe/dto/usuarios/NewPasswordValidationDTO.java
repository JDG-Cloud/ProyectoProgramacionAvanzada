package co.edu.uniquindio.CommuSafe.dto.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewPasswordValidationDTO(
        @NotNull @NotBlank String password,
        @NotNull @NotBlank String repeatPassword) {
}
