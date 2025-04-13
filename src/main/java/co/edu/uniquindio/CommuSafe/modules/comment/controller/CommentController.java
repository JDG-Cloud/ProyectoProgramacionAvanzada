package co.edu.uniquindio.CommuSafe.modules.comment.controller;

import co.edu.uniquindio.CommuSafe.modules.category.ResourceNotFoundException;
import co.edu.uniquindio.CommuSafe.modules.comment.dto.*;
import co.edu.uniquindio.CommuSafe.modules.comment.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Obtiene todos los comentarios (solo administrador)
     */
    @GetMapping("/api/comments")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    /**
     * Obtiene comentarios por ID de reporte
     */
    @GetMapping("/reports/{reportId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByReportId(
            @PathVariable @NotBlank(message = "El ID del reporte no puede estar vacío") String reportId) {
        validateReportExists(reportId);
        return ResponseEntity.ok(commentService.getCommentsByReportId(reportId));
    }

    /**
     * Obtiene comentarios activos por ID de reporte
     */
    @GetMapping("/reports/{reportId}/comments/active")
    public ResponseEntity<List<CommentDTO>> getActiveCommentsByReportId(
            @PathVariable @NotBlank(message = "El ID del reporte no puede estar vacío") String reportId) {
        validateReportExists(reportId);
        return ResponseEntity.ok(commentService.getActiveCommentsByReportId(reportId));
    }

    /**
     * Crea un nuevo comentario para un reporte
     */
    @PostMapping("/reports/{reportId}/comments")
    public ResponseEntity<CommentCreationResponseDTO> createComment(
            @PathVariable @NotBlank(message = "El ID del reporte no puede estar vacío") String reportId,
            @Valid @RequestBody CommentCreationRequestDTO commentDTO) {
        // Validar que el reporte existe
        validateReportExists(reportId);

        // Validar concordancia entre path y body
        if (!reportId.equals(commentDTO.reportId())) {
            throw new ValidationException("El ID del reporte en la ruta debe coincidir con el ID del reporte en el cuerpo");
        }

        // Validar que el usuario tiene permisos para comentar en este reporte
        validateUserCanCommentOnReport(commentDTO.userId(), reportId);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentDTO));
    }

    /**
     * Método alternativo para crear comentarios
     */
    @PostMapping("/comment/createComment")
    public ResponseEntity<CommentCreationResponseDTO> addComment(
            @Valid @RequestBody CommentCreationRequestDTO commentDTO) {
        // Validar que el reporte existe
        validateReportExists(commentDTO.reportId());

        // Validar que el usuario tiene permisos para comentar en este reporte
        validateUserCanCommentOnReport(commentDTO.userId(), commentDTO.reportId());

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentDTO));
    }

    /**
     * Obtiene comentarios por ID de usuario
     */
    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserId(
            @PathVariable @NotBlank(message = "El ID del usuario no puede estar vacío") String userId) {
        validateUserExists(userId);
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId));
    }

    /**
     * Cuenta los comentarios por ID de reporte
     */
    @GetMapping("/reports/{reportId}/comments/count")
    public ResponseEntity<Long> countCommentsByReportId(
            @PathVariable @NotBlank(message = "El ID del reporte no puede estar vacío") String reportId) {
        validateReportExists(reportId);
        return ResponseEntity.ok(commentService.countCommentsByReportId(reportId));
    }

    /**
     * Cuenta los comentarios activos por ID de reporte
     */
    @GetMapping("/reports/{reportId}/comments/count-active")
    public ResponseEntity<Long> countActiveCommentsByReportId(
            @PathVariable @NotBlank(message = "El ID del reporte no puede estar vacío") String reportId) {
        validateReportExists(reportId);
        return ResponseEntity.ok(commentService.countActiveCommentsByReportId(reportId));
    }

    /**
     * Obtiene un comentario por su ID (ruta explícita)
     */
    @GetMapping("/comment/getComment/{id}")
    public ResponseEntity<CommentDTO> getComment(
            @PathVariable @NotBlank(message = "El ID del comentario no puede estar vacío") String id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    /**
     * Obtiene un comentario por su ID (ruta RESTful)
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable @NotBlank(message = "El ID del comentario no puede estar vacío") String id) {
        validateCommentExists(id);
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    /**
     * Actualiza un comentario existente
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentModificationResponseDTO> updateComment(
            @PathVariable @NotBlank(message = "El ID del comentario no puede estar vacío") String id,
            @Valid @RequestBody CommentModificationRequestDTO commentDTO) {
        validateCommentExists(id);
        validateUserCanModifyComment(id);
        return ResponseEntity.ok(commentService.updateComment(id, commentDTO));
    }

    /**
     * Eliminación suave de un comentario
     */
    @PutMapping("/comments/{id}/soft-delete")
    public ResponseEntity<CommentDTO> softDeleteComment(
            @PathVariable @NotBlank(message = "El ID del comentario no puede estar vacío") String id) {
        validateCommentExists(id);
        validateUserCanModifyComment(id);
        return ResponseEntity.ok(commentService.softDeleteComment(id));
    }

    /**
     * Elimina un comentario permanentemente
     */
    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteComment(
            @PathVariable @NotBlank(message = "El ID del comentario no puede estar vacío") String id) {
        validateCommentExists(id);
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos de validación

    private void validateReportExists(String reportId) {
        // Implementar lógica para verificar si el reporte existe
        // Si no existe, lanzar ReportNotFoundException
    }

    private void validateUserExists(String userId) {
        // Implementar lógica para verificar si el usuario existe
        // Si no existe, lanzar UserNotFoundException
    }

    private void validateCommentExists(String commentId) {
        try {
            commentService.getCommentById(commentId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Comentario no encontrado con ID: " + commentId);
        }
    }

    private void validateUserCanCommentOnReport(String userId, String reportId) {
        // Implementar lógica para verificar si un usuario puede comentar en un reporte
        // Ejemplo: verificar si el usuario está activo, si tiene permisos, etc.
    }

    private void validateUserCanModifyComment(String commentId) {
        // Implementar lógica para verificar si el usuario actual puede modificar este comentario
        // Por ejemplo, verificar si es el autor o tiene rol de administrador
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String currentUsername = auth.getName();
        // CommentDTO comment = commentService.getCommentById(commentId);
        // if (!comment.userId().equals(currentUsername) && !auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"))) {
        //    throw new AccessDeniedException("No tienes permiso para modificar este comentario");
        // }
    }
}