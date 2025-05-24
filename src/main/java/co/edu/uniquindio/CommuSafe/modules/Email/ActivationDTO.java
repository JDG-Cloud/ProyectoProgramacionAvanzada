package co.edu.uniquindio.CommuSafe.modules.Email;

public record ActivationDTO(
        String email,
        String code
) {
}
