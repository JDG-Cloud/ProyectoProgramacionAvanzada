package co.edu.uniquindio.CommuSafe.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Notification")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Notification {

    private String message;
    private LocalDateTime timestamp;
    private ObjectId notificationId;
    private boolean read;


}
