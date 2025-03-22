package co.edu.uniquindio.CommuSafe.controladores;

import co.edu.uniquindio.CommuSafe.dto.report.CreateReportDTO;
import co.edu.uniquindio.CommuSafe.dto.report.EditReportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportes")
public class ReporteControlador {
    @PostMapping
    public ResponseEntity<String> createReport(@RequestBody CreateReportDTO crearReporte)throws Exception{
        return ResponseEntity.status(200).body("Reporte registrado correctamente");

    }
    @PutMapping
    public ResponseEntity<String> updateReport(@PathVariable String reportId ,@RequestBody EditReportDTO editarReporte)throws Exception{
        return ResponseEntity.status(200).body("Reporte editado correctamente");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReporte(@PathVariable String reportId)throws Exception{
        return ResponseEntity.status(200).body("Reporte eliminado correctamente");

    }
    @PutMapping("/{reportId}/status")
    public ResponseEntity<String> updateReportStatus(@PathVariable String repotId, EditReportDTO editarReporte)throws Exception{
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
