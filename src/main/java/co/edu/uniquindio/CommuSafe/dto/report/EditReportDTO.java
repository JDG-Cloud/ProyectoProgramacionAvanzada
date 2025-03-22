package co.edu.uniquindio.CommuSafe.dto.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record EditReportDTO(
        @NotNull @NotBlank @Length(max =50)String title,
        @NotNull @NotBlank String categories,
        @NotNull @NotBlank @Length(max =100)String description,
        @NotNull @NotBlank List<String> images,
        @NotNull @NotBlank String createdBy
        ) {
}
