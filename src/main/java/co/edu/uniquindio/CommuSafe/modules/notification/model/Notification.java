package co.edu.uniquindio.CommuSafe.modules.notification.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document (collection = "notification")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notification {

        @EqualsAndHashCode.Include
        @Id
        private ObjectId  id;

        private String message;

        private LocalDateTime date;

        private String type;

        private boolean read;

        private ObjectId reportId;

        private ObjectId receiver;
}
