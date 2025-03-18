package co.edu.uniquindio.CommuSafe.servicios;

import co.edu.uniquindio.CommuSafe.dto.report.CreateReportDTO;

public interface ReportService {

    // se define el nombre


    //devuelve un string
    String createReport(CreateReportDTO createReportDTO);
    String deleteReport(String deleteReportId);
    // se nombra la funcion mas no lo que hace
}
