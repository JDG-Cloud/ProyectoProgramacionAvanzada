package co.edu.uniquindio.CommuSafe.modules.report.dto;

import jakarta.validation.constraints.NotBlank;

public record ReportResponseDto(
        @NotBlank(message = "the status cannot be empty")
        String status,
        @NotBlank(message = "the message cannot be empty")
        String message
) {}
