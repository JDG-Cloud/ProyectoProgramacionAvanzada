package co.edu.uniquindio.CommuSafe.modules.comment.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "comment")
public class Comment {
    @Id
    private ObjectId id;

    private String message;
    private LocalDateTime date;
    private ObjectId userId;
    private ObjectId reportId;
    private String createdBy;
    private int score;
    private boolean deleted;
}
