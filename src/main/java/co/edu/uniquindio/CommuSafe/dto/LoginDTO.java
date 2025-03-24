package co.edu.uniquindio.CommuSafe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(
       @NotBlank @Email String email,
       @NotBlank @Length(min = 7) String password
) {

}
