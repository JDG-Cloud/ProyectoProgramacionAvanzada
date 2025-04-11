package co.edu.uniquindio.CommuSafe.modules.notification.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateNotificationResponseDTO {
    private String id;
    private String message;
    private LocalDateTime date;
    private String type;
    private boolean read;
    private String reportId;
    private String receiver;
}
