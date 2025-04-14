package co.edu.uniquindio.CommuSafe.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class UserResponse {
    private String status;
    private String message;
}
