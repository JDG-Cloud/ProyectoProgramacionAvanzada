package co.edu.uniquindio.CommuSafe.modules.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentModificationResponseDTO(
        @NotBlank String id,
        @NotBlank String reportId,
        @NotBlank  int  score,
        @NotBlank  String  message
        ) {
}
