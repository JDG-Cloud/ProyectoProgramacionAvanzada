package co.edu.uniquindio.CommuSafe.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Usuarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    private ObjectId id;

    private String nombre;
    private String ciudad;
    private String telefono;
    @DBRef
    private Ubication ubication;
    private String correo;
    private String password;
    private Rol rol;
    private UserStatus estadoUser;
    private ValidationCode validationCode;
}