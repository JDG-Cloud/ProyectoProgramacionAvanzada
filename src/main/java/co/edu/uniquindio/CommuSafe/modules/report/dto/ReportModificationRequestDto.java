package co.edu.uniquindio.CommuSafe.modules.report.dto;

import jakarta.validation.constraints.NotBlank;

public record ReportModificationRequestDto (
        @NotBlank(message = "the id cannot be empty")
        String id,
        @NotBlank(message = "the title cannot be empty")
        String title,
        @NotBlank(message = "the description cannot be empty")
        String description,
        @NotBlank(message = "the category cannot be empty")
        String category,
        @NotBlank(message = "the client cannot be empty")
        String client
){}
