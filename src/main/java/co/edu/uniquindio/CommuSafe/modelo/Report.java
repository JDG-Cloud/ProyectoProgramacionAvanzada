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
    private String direction;
    private String email;
    private String password;
    private Rol rol;
    private UserStatus estadoUser;
    private ObjectId category;
    private ObjectId userId;
    private StatusReport statusReport;
    private List<String> photos;
    private Ubication ubication;
    private List<Comment> comments;
    private LocalDateTime registerDate;
}