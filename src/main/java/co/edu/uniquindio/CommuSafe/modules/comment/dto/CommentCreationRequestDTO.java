package co.edu.uniquindio.CommuSafe.modules.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentCreationRequestDTO(

        @NotBlank  String  messagge,
        @NotBlank  String  userId,
        @NotBlank  String  reportId,
        @NotBlank  String  createdBy,
        @NotBlank  int  score
) {}
