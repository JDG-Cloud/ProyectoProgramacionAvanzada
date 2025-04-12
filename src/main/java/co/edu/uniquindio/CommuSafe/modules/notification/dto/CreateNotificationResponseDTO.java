package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import java.time.LocalDateTime;

public record CreateNotificationResponseDTO(
        Object id,
        String message,
        LocalDateTime date,
        String type,
        boolean read,
        Object reportId,
        Object receiver
) {}
