package co.edu.uniquindio.CommuSafe.repositorios;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// clase generica
@Repository
public interface ClienteRepo extends MongoRepository <Cliente, ObjectId>{


}
