import co.edu.uniquindio.CommuSafe.modelo.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document("usuarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Report {

    @Id
    private String id;

    private String title;
    private LocalDateTime date;
    private String description;
    private String direction;
    private String email;
    private String password;
    private Rol rol;
    private UserStatus estadoUser;
    private ObjectID category;
    private String userId;
    private StatusReport statusReport;
    private List<String> photos;
    private Ubication ubication;