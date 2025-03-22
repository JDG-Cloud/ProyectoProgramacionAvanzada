package co.edu.uniquindio.CommuSafe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record OlvidoContrasenaDTO(
        @NotBlank @Email String email
) {
}
