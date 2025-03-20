package co.edu.uniquindio.CommuSafe.repositorios;

import co.edu.uniquindio.CommuSafe.modelo.User;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Document
public interface UsuarioDocument extends MongoRepository <User, ObjectId>{

    @Id
    private String usuarioId;

    private String nombre;
    private String email;
    private String telefono;
    private String ciudad;
    private String password;



}
