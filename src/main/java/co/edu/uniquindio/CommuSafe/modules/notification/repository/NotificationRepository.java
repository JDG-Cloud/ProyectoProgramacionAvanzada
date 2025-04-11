package co.edu.uniquindio.CommuSafe.modules.notification.repository;

import co.edu.uniquindio.CommuSafe.modules.notification.model.Notification;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByReceiver(ObjectId receiver);

    List<Notification> findByReceiverAndRead(ObjectId receiver, boolean read);

    List<Notification> findByReportId(ObjectId reportId);

    @Query("{ 'receptor' : ?0 }")
    List<Notification> findAllByReceiverOrderByDateDesc(ObjectId receiver);

    long countByReceiverAndRead(ObjectId receiver, boolean read);x
}
