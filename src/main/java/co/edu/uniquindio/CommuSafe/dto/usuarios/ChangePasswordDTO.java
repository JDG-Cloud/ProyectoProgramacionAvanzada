package co.edu.uniquindio.CommuSafe.dto.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordDTO(
        @Email String email,
       @NotNull @NotBlank String codeValidation,
        @NotNull @NotBlank String newPassword
) {
}
