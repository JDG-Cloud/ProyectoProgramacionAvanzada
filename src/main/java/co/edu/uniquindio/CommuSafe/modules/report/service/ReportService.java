package co.edu.uniquindio.CommuSafe.modules.report.service;

import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportCreationRequestDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportModificationRequestDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportResponseDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    List<ReportDto> getReports();
    ReportResponseDto creation(ReportCreationRequestDto reportCreationRequestDto);
    ReportResponseDto modification(ReportModificationRequestDto reportModificationRequestDto);
    ReportResponseDto remove(@Valid String id);
}
