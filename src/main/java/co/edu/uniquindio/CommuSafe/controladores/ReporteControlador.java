package co.edu.uniquindio.CommuSafe.controladores;

import co.edu.uniquindio.CommuSafe.dto.report.CreateReportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportes")
public class ReporteControlador {
    @PostMapping
    public ResponseEntity<String> crearReporte(@RequestBody CreateReportDTO crearReporte){

    }

    public ResponseEntity<String> editarReporte(@RequestBody EditarReporte editarReporte){

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReporte(@RequestBody )throws Exception{

    }
}
