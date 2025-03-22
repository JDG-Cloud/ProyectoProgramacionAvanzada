package co.edu.uniquindio.CommuSafe.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    private String id;

    private String nombre;
    private String ciudad;
    private String telefonos;
    private String direccion;
    private String correo;
    private String password;
    private Rol rol;
    private UserStatus estadoUser;
}