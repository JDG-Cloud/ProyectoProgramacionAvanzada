package co.edu.uniquindio.CommuSafe.modules.report.dto;

import co.edu.uniquindio.CommuSafe.modules.category.model.Category;
import co.edu.uniquindio.CommuSafe.modules.comment.model.Comment;
import co.edu.uniquindio.CommuSafe.modules.report.model.Location;
import co.edu.uniquindio.CommuSafe.modules.report.model.Multimedia;
import co.edu.uniquindio.CommuSafe.modules.report.model.ReportStatus;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record ReportDto (
    LocalDateTime createdAt,
    String title,
    String description,
    Location location,
    ObjectId category,
    ReportStatus status,
    List<Multimedia> multimedia,
    ObjectId client,
    List<Comment> comments,
    int likes
){}
