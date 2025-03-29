package co.edu.uniquindio.CommuSafe.repositorios;

import co.edu.uniquindio.CommuSafe.modelo.Report;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ReportRepo extends MongoRepository <Report, ObjectId>{

}
