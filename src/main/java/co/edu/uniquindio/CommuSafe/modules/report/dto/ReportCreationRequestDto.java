package co.edu.uniquindio.CommuSafe.modules.report.dto;

import jakarta.validation.constraints.NotBlank;

public record ReportCreationRequestDto (
        @NotBlank(message = "the title cannot be empty")
        String title,
        @NotBlank(message = "the description cannot be empty")
        String description,
        LocationDto location,
        @NotBlank(message = "the category cannot be empty")
        String category,
        @NotBlank(message = "the client cannot be empty")
        String client
){}