package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import java.time.LocalDateTime;

public record NotificationDTO(
        String id,
        String message,
        LocalDateTime date,
        String type,
        boolean read,
        String reportId,
        String receiver
) {}
