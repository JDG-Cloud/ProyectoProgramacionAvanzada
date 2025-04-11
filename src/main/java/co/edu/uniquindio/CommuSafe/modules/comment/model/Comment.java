package co.edu.uniquindio.CommuSafe.modules.comment.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "comments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment {

    @EqualsAndHashCode.Include
    @Id
    private ObjectId commentId;

    private String message;
    private LocalDate date;
    private ObjectId userId;
    private ObjectId reportId;
    private String createdBy;
    private int score;
    private boolean deleted;

    public Comment (){
    }

    public Comment(String message, ObjectId userId, ObjectId reportId, String createdBy, int score) {
        this.message = message;
        this.userId = userId;
        this.reportId = reportId;
        this.createdBy = createdBy;
        this.score = score;
        this.date = LocalDate.now();
        this.deleted = false;
    }
}
