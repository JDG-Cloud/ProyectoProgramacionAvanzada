package co.edu.uniquindio.CommuSafe.modules.report.repository;

import co.edu.uniquindio.CommuSafe.modules.report.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {

}
