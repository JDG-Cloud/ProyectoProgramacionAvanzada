package co.edu.uniquindio.CommuSafe.modules.report.implementation;

import co.edu.uniquindio.CommuSafe.exceptions.CustomException;
import co.edu.uniquindio.CommuSafe.modules.notification.dto.CreateNotificationRequestDTO;
import co.edu.uniquindio.CommuSafe.modules.notification.service.NotificationService;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportCreationRequestDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportModificationRequestDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportResponseDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportDto;
import co.edu.uniquindio.CommuSafe.modules.report.mapper.ReportMapper;
import co.edu.uniquindio.CommuSafe.modules.report.model.Report;
import co.edu.uniquindio.CommuSafe.modules.report.repository.ReportRepository;
import co.edu.uniquindio.CommuSafe.modules.report.service.ReportService;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    @Autowired
    private final ReportRepository reportRepository;
    @Autowired
    private final ReportMapper reportMapper;
    @Autowired
    private final NotificationService notificationService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ReportDto> getReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(reportMapper::toDTOList).collect(Collectors.toList());
    }

    @Override
    public ReportResponseDto creation(ReportCreationRequestDto reportCreationRequestDto) {
        try {
            Report report = reportMapper.toEntity(reportCreationRequestDto);

            Report insertedReport = mongoTemplate.insert(report);

            if (insertedReport != null) {
                notificationService.createNotification(new CreateNotificationRequestDTO("A new report has been created","report_creation",insertedReport.getId().toString(),insertedReport.getClient().toString()));
                return new ReportResponseDto("OK", "report created");
            }
            throw new CustomException(HttpStatus.BAD_REQUEST, "report creation failed");
        } catch (DataAccessException e1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw new CustomException(e2.getStatus(), e2.getMessage());
        } catch (Exception e3) {
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }

    @Override
    public ReportResponseDto modification(ReportModificationRequestDto reportModificationRequestDto) {

        try {

            Query query = new Query(Criteria.where("_id").is(reportModificationRequestDto.id()));

            boolean exists = mongoTemplate.exists(query, Report.class);

            if(!exists) {
                throw  new  CustomException(HttpStatus.NOT_FOUND,"report not exists");
            }

            Update update = new Update()
                    .set("title", reportModificationRequestDto.title())
                    .set("description", reportModificationRequestDto.description())
                    .set("category", reportModificationRequestDto.category())
                    .set("client", reportModificationRequestDto.client());


            UpdateResult result = mongoTemplate.updateFirst(query, update, Report.class);

            if (result.getMatchedCount() == 0) {
                throw new CustomException(HttpStatus.NOT_FOUND, "report not found");
            }
            notificationService.createNotification(new CreateNotificationRequestDTO("A report has been modificated","report_modification",reportModificationRequestDto.id(),reportModificationRequestDto.client()));
            return new ReportResponseDto("OK","report updated successfully");
        } catch (DataAccessException e1) {
            throw  new CustomException(HttpStatus.BAD_REQUEST,String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw  new CustomException(e2.getStatus(),e2.getMessage());
        } catch (Exception e3) {
            throw  new CustomException(HttpStatus.BAD_REQUEST,String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }

    @Override
    public ReportResponseDto remove(String id) {
        try {

            Query query = new Query(Criteria.where("_id").is(id));

            boolean exists = mongoTemplate.exists(query, Report.class);

            if(!exists) {
                throw  new  CustomException(HttpStatus.NOT_FOUND,"report not exists");
            }

            Update update = new Update()
                    .set("active", false);

            UpdateResult result = mongoTemplate.updateFirst(query, update, Report.class);

            if (result.getMatchedCount() == 0) {
                throw new CustomException(HttpStatus.NOT_FOUND, "report not found");
            }

            Report report = mongoTemplate.findOne(query, Report.class);

            notificationService.createNotification(new CreateNotificationRequestDTO("A report has been remove","report_remove",report.getId().toString(),report.getClient().toString()));
            return new ReportResponseDto("OK","report remove successfully");
        } catch (DataAccessException e1) {
            throw  new CustomException(HttpStatus.BAD_REQUEST,String.format("database error %s", e1.getMessage()));
        } catch (CustomException e2) {
            throw  new CustomException(e2.getStatus(),e2.getMessage());
        } catch (Exception e3) {
            throw  new CustomException(HttpStatus.BAD_REQUEST,String.format("an unexpected error occurred: %s", e3.getMessage()));
        }
    }
}

