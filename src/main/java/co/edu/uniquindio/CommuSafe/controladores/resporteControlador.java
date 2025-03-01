package co.edu.uniquindio.CommuSafe.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class resporteControlador {
    @PostMapping
    public ResponseEntity<String> crearReporte(@RequestBody CrearReporte crearReporte){

    }
    public ResponseEntity<String> editarReporte(@RequestBody EditarReporte editarReporte){

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReporte(@RequestBody )throws Exception{

    }
}
