package co.edu.uniquindio.CommuSafe.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDTO(
        @NotBlank @NotNull String uuid,
       @NotBlank @NotNull String title,
        @NotBlank @NotNull String description
) {
}
