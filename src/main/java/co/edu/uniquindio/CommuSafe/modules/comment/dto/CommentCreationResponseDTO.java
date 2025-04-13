package co.edu.uniquindio.CommuSafe.modules.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public record CommentCreationResponseDTO(
        @NotBlank String message,
        LocalDateTime date,
        @NotBlank String userId,
        @NotBlank String reportId,
        @NotBlank String createdBy,
        int score,
        boolean deleted
){}
