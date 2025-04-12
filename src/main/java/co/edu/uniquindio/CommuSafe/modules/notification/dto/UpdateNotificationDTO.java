package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateNotificationDTO(
        @NotBlank(message = "El mensaje no puede estar vacío")
        @Length(max = 300)
        String message,

        @NotBlank(message = "El tipo no puede estar vacío")
        @Length(max = 50)
        String type,

        boolean read
){}
