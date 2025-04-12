package co.edu.uniquindio.CommuSafe.modules.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentCreationRequestDTO(

        @NotBlank  String  message,
        @NotBlank  String  userId,
        @NotBlank  String  reportId,
        @NotBlank  String  createdBy,
        int  score
) {}
