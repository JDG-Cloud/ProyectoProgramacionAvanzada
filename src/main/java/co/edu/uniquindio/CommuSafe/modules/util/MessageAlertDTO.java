package co.edu.uniquindio.CommuSafe.modules.util;

public record MessageAlertDTO<T>(boolean error, T message) {
}