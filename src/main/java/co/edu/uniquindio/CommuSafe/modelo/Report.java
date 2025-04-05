package co.edu.uniquindio.CommuSafe.modelo;

import co.edu.uniquindio.CommuSafe.modelo.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.util.List;


@Document("Reports")
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Report {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;

    private String title;
    private LocalDateTime date;
    private String description;
    private ObjectId category;
    private ObjectId userId;
    private StatusReport statusReport;
    private List<String> photos;
    private Ubication ubication;
    private List<Comment> comments;
    private LocalDateTime registerDate;

    @Builder

    public Report(String title, LocalDateTime date, String description, ObjectId category, ObjectId userId, StatusReport statusReport, List<String> photos, Ubication ubication, List<Comment> comments, LocalDateTime registerDate) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.category = category;
        this.userId = userId;
        this.statusReport = statusReport;
        this.photos = photos;
        this.ubication = ubication;
        this.comments = comments;
        this.registerDate = registerDate;
    }
}