package co.edu.uniquindio.CommuSafe.repositorios;

import co.edu.uniquindio.CommuSafe.modelo.Notification;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepo extends MongoRepository <Notification, ObjectId>{

}
