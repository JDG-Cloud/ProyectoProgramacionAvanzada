package co.edu.uniquindio.CommuSafe.modules.report.model;

import co.edu.uniquindio.CommuSafe.modules.comment.model.Comment;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class
Report {
    private ObjectId id;
    private LocalDateTime createdAt;
    private String title;
    private String description;
    private Location location;
    private ObjectId category;
    private ReportStatus status;
    private List<Multimedia> multimedia;
    private ObjectId client;
    private List<Comment> comments;
    private int likes;
    private boolean active;
}
