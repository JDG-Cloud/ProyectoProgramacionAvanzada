package co.edu.uniquindio.CommuSafe.dto;

import jakarta.validation.constraints.NotBlank;

public record RestablecerContrasenaDTO(
        @NotBlank String code
) {
}
