package co.edu.uniquindio.CommuSafe.modules.comment.implementation;

import co.edu.uniquindio.CommuSafe.exceptions.CommentNotFoundException;
import co.edu.uniquindio.CommuSafe.modules.comment.dto.*;
import co.edu.uniquindio.CommuSafe.modules.comment.mapper.CommentMapper;
import co.edu.uniquindio.CommuSafe.modules.comment.model.Comment;
import co.edu.uniquindio.CommuSafe.modules.comment.repository.CommentRepository;
import co.edu.uniquindio.CommuSafe.modules.comment.service.CommentService;
import co.edu.uniquindio.CommuSafe.modules.notification.dto.CreateNotificationRequestDTO;
import co.edu.uniquindio.CommuSafe.modules.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final CommentMapper commentMapper;

    @Autowired
    private final NotificationService notificationService;

    @Override
    public List<CommentDTO> getAllComments() {
        log.info("getting all system feedback");
        List<Comment> comments = commentRepository.findAll();
        log.info("{} comments found", comments.size());
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByReportId(String reportId) {
        log.info("seeking comments for report with ID: {}", reportId);
        ObjectId reportObjectId = new ObjectId(reportId);
        List<Comment> comments = commentRepository.findAllByReportIdOrderByDateDesc(reportObjectId);
        log.info("{} comments were found for the report {}", comments.size(), reportId);
        return comments
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getActiveCommentsByReportId(String reportId) {
        log.info("looking for active comments for report with ID: {}", reportId);
        ObjectId reportObjectId = new ObjectId(reportId);
        List<Comment> activeComments = commentRepository.findActiveCommentsByReportId(reportObjectId);
        log.info("{} active comments were found for the report {}", activeComments.size(), reportId);
        return activeComments
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByUserId(String userId) {
        log.info("searching for comments made by user with ID: {}", userId);
        ObjectId userObjectId = new ObjectId(userId);
        List<Comment> userComments = commentRepository.findByUserId(userObjectId);
        log.info("{} user comments found {}", userComments.size(), userId);
        return userComments
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(String id) {
        log.info("searching for comment with ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("no comments found with ID: {}", id);
                    return new CommentNotFoundException("no comments found with ID: " + id);
                });
        log.info("comment found: {}", comment);
        return commentMapper.toDTO(comment);
    }

    @Override
    public CommentCreationResponseDTO createComment(CommentCreationRequestDTO commentDTO) {
        log.info("creating a new comment for the report: {}", commentDTO.reportId());
        Comment comment = commentMapper.toEntity(commentDTO);
        Comment savedComment = commentRepository.save(comment);
        log.info("comment created successfully with ID: {}", savedComment.getId());

        // Crear notificación para el propietario del reporte
        CreateNotificationRequestDTO notificationDTO = new CreateNotificationRequestDTO("new comment on your report","NEW_COMMENT",commentDTO.reportId(),commentDTO.userId());

        log.info("sending new comment notification to the user: {}", commentDTO.userId());
        notificationService.createNotification(notificationDTO);

        return commentMapper.toCreationResponseDTO(savedComment);
    }

    @Override
    public CommentModificationResponseDTO updateComment(CommentModificationRequestDTO commentDTO) {
        log.info("updating comment with ID: {}", commentDTO.id());
        Comment comment = commentRepository.findById(commentDTO.id())
                .orElseThrow(() -> {
                    log.error("no comments found with ID: {}", commentDTO.id());
                    return new CommentNotFoundException("no comments found with ID: " + commentDTO.id());
                });

        comment.setMessage(commentDTO.message());
        comment.setScore(commentDTO.score());

        Comment updatedComment = commentRepository.save(comment);
        log.info("comment updated successfully: {}", updatedComment);

        // Crear notificación para la actualización
        CreateNotificationRequestDTO notificationDTO = new CreateNotificationRequestDTO("a comment on your report has been updated.","UPDATED_COMMENT",commentDTO.reportId(),commentDTO.userId());

        log.info("sending comment update notification to the user: {}", commentDTO.userId());
        notificationService.createNotification(notificationDTO);

        return commentMapper.toModificationResponseDTO(updatedComment);
    }

    @Override
    public CommentDTO softDeleteComment(String id) {
        log.info("performing soft deletion of comment with ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("no comments found with ID: {}", id);
                    return new CommentNotFoundException("no comments found with ID: " + id);
                });

        comment.setDeleted(true);
        Comment deletedComment = commentRepository.save(comment);
        log.info("comment marked as successfully deleted: {}", deletedComment);

        return commentMapper.toDTO(deletedComment);
    }

    @Override
    public void deleteComment(String id) {
        log.info("permanently deleting comment with ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("no comments found with ID: {}", id);
                    return new CommentNotFoundException("no comments found with ID: " + id);
                });

        commentRepository.deleteById(id);
        log.info("comment permanently deleted successfully");

        // Opcional: Crear notificación para la eliminación
        CreateNotificationRequestDTO notificationDTO = new CreateNotificationRequestDTO("a comment on your report has been deleted","COMMENT_DELETED",comment.getReportId().toString(),comment.getUserId().toString());

        log.info("sending comment deletion notification to the user: {}", comment.getUserId());
        notificationService.createNotification(notificationDTO);
    }

    @Override
    public long countCommentsByReportId(String reportId) {
        log.info("counting all comments for the report with ID: {}", reportId);
        ObjectId reportObjectId = new ObjectId(reportId);
        long count = commentRepository.countByReportId(reportObjectId);
        log.info("total comments for the report {}: {}", reportId, count);
        return count;
    }

    @Override
    public long countActiveCommentsByReportId(String reportId) {
        log.info("counting active comments for report with ID: {}", reportId);
        ObjectId reportObjectId = new ObjectId(reportId);
        long activeCount = commentRepository.countByReportIdAndDeleted(reportObjectId, false);
        log.info("total active comments for the report {}: {}", reportId, activeCount);
        return activeCount;
    }
}