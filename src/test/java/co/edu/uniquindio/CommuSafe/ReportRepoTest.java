package co.edu.uniquindio.CommuSafe;

import co.edu.uniquindio.CommuSafe.modelo.Report;
import co.edu.uniquindio.CommuSafe.modelo.Ubication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportRepoTest {

    @Test
    public void registrarTest() {
        //Creamos un cliente con sus propiedades
        Report report = Report.builder()
                .title = ("Robo");
            .date = date;
            .description = ("Cuidado por robo");
            .category = category;
            .userId = userId;
            .statusReport = statusReport;
            .photos = photos;
            .ubication = (new Ubication(1.5, 1.0));
            .comments = comments;
            .registerDate = registerDate;
    }
}
