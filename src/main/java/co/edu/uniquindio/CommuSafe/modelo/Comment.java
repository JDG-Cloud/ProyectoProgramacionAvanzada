package co.edu.uniquindio.CommuSafe.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document("Comments")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comment {
@Id
    private ObjectId commentId;
// todo lo que sea ID es ObjectId
    private ObjectId reportId;
    private String message;
    private LocalDateTime date;
    private ObjectId userId;

    @Builder

    public Comment(ObjectId commentId, ObjectId reportId, String message, LocalDateTime date, ObjectId userId) {
        this.commentId = commentId;
        this.reportId = reportId;
        this.message = message;
        this.date = date;
        this.userId = userId;
    }
}
