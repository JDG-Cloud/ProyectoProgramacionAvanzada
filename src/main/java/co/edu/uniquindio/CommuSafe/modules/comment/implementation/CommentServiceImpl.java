package co.edu.uniquindio.CommuSafe.modules.comment.implementation;

import co.edu.uniquindio.CommuSafe.modules.comment.CommentNotFoundException;
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
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final CommentMapper commentMapper;

    @Autowired
    private final NotificationService notificationService;

    @Override
    public List<CommentDTO> getAllComments() {
        log.info("Obteniendo todos los comentarios del sistema");
        List<Comment> comments = commentRepository.findAll();
        log.info("Se encontraron {} comentarios", comments.size());
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByReportId(String reportId) {
        log.info("Buscando comentarios para el reporte con ID: {}", reportId);
        ObjectId reportObjectId = new ObjectId(reportId);
        List<Comment> comments = commentRepository.findAllByReportIdOrderByDateDesc(reportObjectId);
        log.info("Se encontraron {} comentarios para el reporte {}", comments.size(), reportId);
        return comments
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getActiveCommentsByReportId(String reportId) {
        log.info("Buscando comentarios activos para el reporte con ID: {}", reportId);
        ObjectId reportObjectId = new ObjectId(reportId);
        List<Comment> activeComments = commentRepository.findActiveCommentsByReportId(reportObjectId);
        log.info("Se encontraron {} comentarios activos para el reporte {}", activeComments.size(), reportId);
        return activeComments
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByUserId(String userId) {
        log.info("Buscando comentarios realizados por el usuario con ID: {}", userId);
        ObjectId userObjectId = new ObjectId(userId);
        List<Comment> userComments = commentRepository.findByUserId(userObjectId);
        log.info("Se encontraron {} comentarios del usuario {}", userComments.size(), userId);
        return userComments
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(String id) {
        log.info("Buscando comentario con ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se encontró ningún comentario con ID: {}", id);
                    return new CommentNotFoundException("No se encontró ningún comentario con ID: " + id);
                });
        log.info("Comentario encontrado: {}", comment);
        return commentMapper.toDTO(comment);
    }

    @Override
    public CommentCreationResponseDTO createComment(CommentCreationRequestDTO commentDTO) {
        log.info("Creando nuevo comentario para el reporte: {}", commentDTO.reportId());
        Comment comment = commentMapper.toEntity(commentDTO);
        Comment savedComment = commentRepository.save(comment);
        log.info("Comentario creado con éxito con ID: {}", savedComment.getId());

        // Crear notificación para el propietario del reporte
        CreateNotificationRequestDTO notificationDTO = new CreateNotificationRequestDTO();
        notificationDTO.setMessage("Nuevo comentario en tu reporte");
        notificationDTO.setType("NUEVO_COMENTARIO");
        notificationDTO.setReportId(commentDTO.reportId());
        notificationDTO.setReceiver(commentDTO.userId()); // En un escenario real, este debería ser el ID del propietario del reporte

        log.info("Enviando notificación de nuevo comentario al usuario: {}", commentDTO.userId());
        notificationService.createNotification(notificationDTO);

        return commentMapper.toCreationResponseDTO(savedComment);
    }

    @Override
    public CommentModificationResponseDTO updateComment(String id, CommentModificationRequestDTO commentDTO) {
        log.info("Actualizando comentario con ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se encontró ningún comentario con ID: {}", id);
                    return new CommentNotFoundException("No se encontró ningún comentario con ID: " + id);
                });

        comment.setMessage(commentDTO.message());
        comment.setScore(commentDTO.score());

        Comment updatedComment = commentRepository.save(comment);
        log.info("Comentario actualizado con éxito: {}", updatedComment);

        // Crear notificación para la actualización
        CreateNotificationRequestDTO notificationDTO = new CreateNotificationRequestDTO();
        notificationDTO.setMessage("Un comentario en tu reporte ha sido actualizado");
        notificationDTO.setType("COMENTARIO_ACTUALIZADO");
        notificationDTO.setReportId(commentDTO.reportId());
        notificationDTO.setReceiver(commentDTO.userId()); // En un escenario real, este debería ser el ID del propietario del reporte

        log.info("Enviando notificación de actualización de comentario al usuario: {}", commentDTO.userId());
        notificationService.createNotification(notificationDTO);

        return commentMapper.toModificationResponseDTO(updatedComment);
    }

    @Override
    public CommentDTO softDeleteComment(String id) {
        log.info("Realizando eliminación suave del comentario con ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se encontró ningún comentario con ID: {}", id);
                    return new CommentNotFoundException("No se encontró ningún comentario con ID: " + id);
                });

        comment.setDeleted(true);
        Comment deletedComment = commentRepository.save(comment);
        log.info("Comentario marcado como eliminado con éxito: {}", deletedComment);

        return commentMapper.toDTO(deletedComment);
    }

    @Override
    public void deleteComment(String id) {
        log.info("Eliminando permanentemente el comentario con ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se encontró ningún comentario con ID: {}", id);
                    return new CommentNotFoundException("No se encontró ningún comentario con ID: " + id);
                });

        commentRepository.deleteById(id);
        log.info("Comentario eliminado permanentemente con éxito");

        // Opcional: Crear notificación para la eliminación
        CreateNotificationRequestDTO notificationDTO = new CreateNotificationRequestDTO();
        notificationDTO.setMessage("Un comentario en tu reporte ha sido eliminado");
        notificationDTO.setType("COMENTARIO_ELIMINADO");
        notificationDTO.setReportId(comment.getReportId().toString());
        notificationDTO.setReceiver(comment.getUserId().toString());

        log.info("Enviando notificación de eliminación de comentario al usuario: {}", comment.getUserId());
        notificationService.createNotification(notificationDTO);
    }

    @Override
    public long countCommentsByReportId(String reportId) {
        log.info("Contando todos los comentarios para el reporte con ID: {}", reportId);
        ObjectId reportObjectId = new ObjectId(reportId);
        long count = commentRepository.countByReportId(reportObjectId);
        log.info("Total de comentarios para el reporte {}: {}", reportId, count);
        return count;
    }

    @Override
    public long countActiveCommentsByReportId(String reportId) {
        log.info("Contando comentarios activos para el reporte con ID: {}", reportId);
        ObjectId reportObjectId = new ObjectId(reportId);
        long activeCount = commentRepository.countByReportIdAndDeleted(reportObjectId, false);
        log.info("Total de comentarios activos para el reporte {}: {}", reportId, activeCount);
        return activeCount;
    }
}