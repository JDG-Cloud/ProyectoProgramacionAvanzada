package co.edu.uniquindio.CommuSafe.modules.comment.dto;

import java.time.LocalDateTime;

public record CommentDTO(
        String id,
        String message,
        LocalDateTime date,
        String userId,
        String reportId,
        String createdBy,
        int score,
        boolean deleted

){}
