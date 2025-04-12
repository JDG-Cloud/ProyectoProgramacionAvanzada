package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import java.time.LocalDateTime;

public record NotificationDTO(
        Object id,
        String message,
        LocalDateTime date,
        String type,
        boolean read,
        Object reportId,
        Object receiver
) {}
