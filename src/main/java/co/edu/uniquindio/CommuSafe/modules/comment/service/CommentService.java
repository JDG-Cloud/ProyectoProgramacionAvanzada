package co.edu.uniquindio.CommuSafe.modules.comment.service;

import co.edu.uniquindio.CommuSafe.modules.comment.dto.*;
import java.util.List;

public class CommentService {

    List<CommentDTO> getAllComments();
    List<CommentDTO> getCommentsByReportId(String reportId);
    List<CommentDTO> getActiveCommentsByReportId(String reportId);
    List<CommentDTO> getCommentsByUserId(String userId);
    CommentDTO getCommentById(String id);
    CommentCreationResponseDTO createComment(CommentCreationRequestDTO commentDTO);
    CommentModificationResponseDTO updateComment(String id, CommentModificationRequestDTO commentDTO);
    CommentDTO softDeleteComment(String id);
    void deleteComment(String id);
    long countCommentsByReportId(String reportId);
    long countActiveCommentsByReportId(String reportId);
}
