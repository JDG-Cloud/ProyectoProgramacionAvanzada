package co.edu.uniquindio.CommuSafe.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
    private Date dateRegister;

    @Builder
    public User(String nombre, String ciudad, String telefono, Ubication ubication,
                String correo, String password, Rol rol, UserStatus estadoUser,
            ValidationCode validationCode, String photoUser,Date dateRegister) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.ubication = ubication;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.estadoUser = estadoUser;
        this.validationCode = validationCode;
        this.photoUser = photoUser;
        this.dateRegister = new Date();
    }
}