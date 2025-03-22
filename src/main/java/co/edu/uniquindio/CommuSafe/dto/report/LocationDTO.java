package co.edu.uniquindio.CommuSafe.dto.report;

public class LocationDTO {
    public record Location(
            String altitude,
            String Longitude
    ) {
    }
}
