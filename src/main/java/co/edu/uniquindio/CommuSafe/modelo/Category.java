package co.edu.uniquindio.CommuSafe.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private ObjectId idCategory;
    private String nombre;
}
