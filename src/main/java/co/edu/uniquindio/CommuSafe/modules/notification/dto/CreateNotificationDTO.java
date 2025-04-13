package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateNotificationDTO (
        @NotBlank(message = "the message cannot be empty")
        @Length(max = 300)
        String message,

        @NotBlank(message = "the type cannot be empty")
        @Length(max = 50)
        String type,

        String reportId,

        @NotBlank(message = "the receiver is required")
        String receiver
) {}
