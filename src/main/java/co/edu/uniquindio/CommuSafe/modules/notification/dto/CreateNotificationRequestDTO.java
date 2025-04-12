package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import jakarta.validation.constraints.NotEmpty;

public record CreateNotificationRequestDTO(
        @NotEmpty(message = "El mensaje de la notificación no puede estar vacío.")
        String message,

        @NotEmpty(message = "El tipo de la notificación no puede estar vacío.")
        String type,

        String reportId,

        @NotEmpty(message = "El receptor de la notificación no puede estar vacío.")
        String receiver
) {}
