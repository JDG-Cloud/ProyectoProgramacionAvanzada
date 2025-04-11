package co.edu.uniquindio.CommuSafe.modules.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentModificationResponseDTO(
        @NotNull @NotBlank String token
        ) {
}
