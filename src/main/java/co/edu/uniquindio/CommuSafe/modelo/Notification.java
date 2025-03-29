package co.edu.uniquindio.CommuSafe.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Notification")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notification {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId notificationId;

    private String message;
    private LocalDateTime timestamp;
    private boolean read;

    @Builder
    public Notification(String message, LocalDateTime timestamp, ObjectId notificationId, boolean read) {
        this.message = message;
        this.timestamp = timestamp;
        this.notificationId = notificationId;
        this.read = read;
    }
}
