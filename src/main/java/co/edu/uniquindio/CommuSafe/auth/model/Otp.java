package co.edu.uniquindio.CommuSafe.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "otp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
    @Id
    private String code;
    private Date createdAt;
}
