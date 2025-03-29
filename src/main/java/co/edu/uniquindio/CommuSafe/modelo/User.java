package co.edu.uniquindio.CommuSafe.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Usuarios")
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;

    private String nombre;
    private String ciudad;
    private String telefono;
    private Ubication ubication;
    private String correo;
    private String password;
    private Rol rol;
    private UserStatus estadoUser;
    private ValidationCode validationCode;
    private String photoUser;
}