package co.edu.uniquindio.CommuSafe.dto.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

    public record CreateReportDTO(
            @NotNull @NotBlank @Length(max =50)String title,
            @NotNull @NotBlank String categories,
            @NotNull @NotBlank @Length(max =400)String description,
            @NotNull @NotBlank LocationDTO location,
            @NotNull @NotBlank List<String> images,
            @NotNull @NotBlank StatusDTO status,
            @NotNull @NotBlank String createdBy

    ) {
    }

