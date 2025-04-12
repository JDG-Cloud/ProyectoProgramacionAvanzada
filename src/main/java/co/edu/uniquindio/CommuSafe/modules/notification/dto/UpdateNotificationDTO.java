package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateNotificationDTO(
        @NotBlank(message = "message must not be empty")
        @Length(max = 300)
        String message,

        @NotBlank(message = "the type cannot be empty")
        @Length(max = 50)
        String type,

        boolean read
){}
