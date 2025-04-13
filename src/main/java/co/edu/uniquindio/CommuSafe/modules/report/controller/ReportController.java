package co.edu.uniquindio.CommuSafe.modules.report.controller;

import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportCreationRequestDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportModificationRequestDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportResponseDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportDto;
import co.edu.uniquindio.CommuSafe.modules.report.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * Get reports
     */
    @GetMapping
    public ResponseEntity<List<ReportDto>> getReports() {
        List<ReportDto> reports = reportService.getReports();
        return ResponseEntity.ok(reports);
    }

    /**
     * report creation
     */
    @PostMapping
    public ResponseEntity<ReportResponseDto> creation(@Valid @RequestBody ReportCreationRequestDto reportCreationRequestDto) {
        ReportResponseDto response = reportService.creation(reportCreationRequestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * report modification
     */
    @PutMapping
    public ResponseEntity<ReportResponseDto> modification(@Valid @RequestBody ReportModificationRequestDto reportModificationRequestDto) {
        ReportResponseDto response = reportService.modification(reportModificationRequestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * report remove
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ReportResponseDto> remove(@PathVariable String id) {
        ReportResponseDto response = reportService.remove(id);
        return ResponseEntity.ok(response);
    }
}