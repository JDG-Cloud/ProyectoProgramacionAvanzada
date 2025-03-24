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
@NoArgsConstructor
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
    @DBRef
    private Rol rol;
    private UserStatus estadoUser;
    @DBRef
    private ObjectId category;
    @DBRef
    private ObjectId userId;
    private StatusReport statusReport;
    private List<String> photos;
    @DBRef
    private Ubication ubication;
    @DBRef
    private List<Comment> comments;
    private LocalDateTime registerDate;
}