package co.edu.uniquindio.CommuSafe.modules.notification.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document (collection = "notification")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notification {

    @EqualsAndHashCode.Include
    @Id
    private Object id;

    private String message;

    private LocalDateTime date;

    private String type;

    private boolean read;

    private ObjectId reportId;

    private ObjectId receiver;

    public Notification(){
    }

    public Notification(String message, String type) {
        this.message = message;
        this.type = type;
        this.date = LocalDateTime.now();
        this.read = false;
    }
}
