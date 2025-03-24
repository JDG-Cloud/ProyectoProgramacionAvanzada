package co.edu.uniquindio.CommuSafe.dto;

public record MessageDTO<T>(boolean error, T message) {
}
