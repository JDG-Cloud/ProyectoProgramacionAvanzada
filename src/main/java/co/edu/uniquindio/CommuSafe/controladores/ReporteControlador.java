package co.edu.uniquindio.CommuSafe.controladores;

import co.edu.uniquindio.CommuSafe.dto.MessageDTO;
import co.edu.uniquindio.CommuSafe.dto.report.CreateReportDTO;
import co.edu.uniquindio.CommuSafe.dto.report.EditReportDTO;
import co.edu.uniquindio.CommuSafe.dto.report.ReportStatusDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**/
@RestController
@RequestMapping("/reportes")
public class ReporteControlador {
    @PostMapping
    public ResponseEntity<MessageDTO<String>> createReport(@Valid @RequestBody CreateReportDTO crearReporte)throws Exception{
        return ResponseEntity.status(200).body(new MessageDTO<>(false,"Reporte registrado correctamente"));

    }
    @PutMapping("/{reportId}")
    public ResponseEntity<MessageDTO<String>> updateReport(@PathVariable String reportId ,@RequestBody EditReportDTO editarReporte)throws Exception{
        return ResponseEntity.status(200).body(new MessageDTO<>(false,"Reporte actualizado correctamente"));

    }
    @DeleteMapping("/{reportId}")
    public ResponseEntity<String> deleteReporte(@PathVariable String reportId)throws Exception{
        return ResponseEntity.status(200).body("Reporte eliminado correctamente");

    }
    @PutMapping("/{reportId}/status")
    public ResponseEntity<String> updateReportStatus(@PathVariable String repotId, ReportStatusDTO editarReporte)throws Exception{
        return ResponseEntity.status(200).body("Reporte actualizado correctamente");

    }
    @GetMapping
    public ResponseEntity<String> getReports()throws Exception{
        return ResponseEntity.status(200).body("Reporte enviado correctamente");

    }
    @PostMapping("/{reportId}/importance")
    public ResponseEntity<String> markReportAsImportant(@PathVariable String reportId) {
        // Implementación pendiente
        return ResponseEntity.status(200).body("Enviada importancia");
    }
}
