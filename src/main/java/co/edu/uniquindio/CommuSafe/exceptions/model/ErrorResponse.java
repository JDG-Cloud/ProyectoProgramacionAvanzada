package co.edu.uniquindio.CommuSafe.exceptions.model;

public class ErrorResponse {
    private int status;
    private String description;
    private String message;

    public ErrorResponse(int status,String description, String message) {
        this.status = status;
        this.description = description;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }
    public String getDescription() {
        return description;
    }
    public String getMessage() {
        return message;
    }
}
