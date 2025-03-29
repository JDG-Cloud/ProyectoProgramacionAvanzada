package co.edu.uniquindio.CommuSafe.modelo;

import java.time.LocalDateTime;

public class ValidationCode {
    private String code;
    private LocalDateTime date;

    public ValidationCode(String code, LocalDateTime date) {
        this.code = code;
        this.date = date;
    }
}
