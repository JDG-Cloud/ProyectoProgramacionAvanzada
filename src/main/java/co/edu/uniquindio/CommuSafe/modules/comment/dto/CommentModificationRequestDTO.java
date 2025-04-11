package co.edu.uniquindio.CommuSafe.modules.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentModificationRequestDTO(
        @NotBlank String  id,
        @NotBlank  String  message,
        @NotBlank  String  reportId,
        @NotBlank  String  userId,
        @NotBlank  String  createdBy,
        @NotBlank  int  score
){}
