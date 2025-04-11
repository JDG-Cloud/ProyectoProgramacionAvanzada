package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter

public class CreateNotificationRequestDTO {

    @NotEmpty(message = "El mensaje de la notificación no puede estar vacío.")
    private String message;

    @NotEmpty(message = "El tipo de la notificación no puede estar vacío.")
    private String type;

    private String reportId;

    @NotEmpty(message = "El receptor de la notificación no puede estar vacío.")
    private String receiver;
}
