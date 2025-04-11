package co.edu.uniquindio.CommuSafe.modules.comment.controller;

import co.edu.uniquindio.CommuSafe.modules.comment.dto.*;
import co.edu.uniquindio.CommuSafe.modules.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Utiliza el patrón de ruta anidada del fragmento
     */
    @GetMapping("/api/reports/{reportId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByReportId(@PathVariable String reportId) {
        return ResponseEntity.ok(commentService.getCommentsByReportId(reportId));
    }

    /**
     * Obtiene comentarios activos por ID de reporte
     */
    @GetMapping("/api/reports/{reportId}/comments/active")
    public ResponseEntity<List<CommentDTO>> getActiveCommentsByReportId(@PathVariable String reportId) {
        return ResponseEntity.ok(commentService.getActiveCommentsByReportId(reportId));
    }

    /**
     * Crea un nuevo comentario para un reporte
     * Utiliza el patrón de ruta anidada del fragmento
     */
    @PostMapping("/api/reports/{reportId}/comments")
    public ResponseEntity<CommentCreationResponseDTO> createComment(
            @PathVariable String reportId,
            @Valid @RequestBody CommentCreationRequestDTO commentDTO) {
        // Asegura que el ID del reporte en la ruta coincida con el del cuerpo de la solicitud
        if (!reportId.equals(commentDTO.reportId())) {
            throw new IllegalArgumentException("El ID del reporte en la ruta debe coincidir con el ID del reporte en el cuerpo de la solicitud");
        }
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    /**
     * Método alternativo utilizando el patrón de nomenclatura explícito del fragmento
     */
    @PostMapping("/api/comment/createComment")
    public ResponseEntity<CommentCreationResponseDTO> addComment(
            @Valid @RequestBody CommentCreationRequestDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    /**
     * Obtiene comentarios por ID de usuario
     */
    @GetMapping("/api/users/{userId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId));
    }

    /**
     * Cuenta los comentarios por ID de reporte
     */
    @GetMapping("/api/reports/{reportId}/comments/count")
    public ResponseEntity<Long> countCommentsByReportId(@PathVariable String reportId) {
        return ResponseEntity.ok(commentService.countCommentsByReportId(reportId));
    }

    /**
     * Cuenta los comentarios activos por ID de reporte
     */
    @GetMapping("/api/reports/{reportId}/comments/count-active")
    public ResponseEntity<Long> countActiveCommentsByReportId(@PathVariable String reportId) {
        return ResponseEntity.ok(commentService.countActiveCommentsByReportId(reportId));
    }

    /**
     * Obtiene un comentario por su ID
     * Soporta el patrón del fragmento
     */
    @GetMapping("/api/comment/getComment/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    /**
     * También soporta una ruta más RESTful para obtener un comentario por ID
     */
    @GetMapping("/api/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    /**
     * Actualiza un comentario existente
     */
    @PutMapping("/api/comments/{id}")
    public ResponseEntity<CommentModificationResponseDTO> updateComment(
            @PathVariable String id,
            @Valid @RequestBody CommentModificationRequestDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(id, commentDTO));
    }

    /**
     * Eliminación suave de un comentario (marcarlo como eliminado)
     */
    @PutMapping("/api/comments/{id}/soft-delete")
    public ResponseEntity<CommentDTO> softDeleteComment(@PathVariable String id) {
        return ResponseEntity.ok(commentService.softDeleteComment(id));
    }

    /**
     * Elimina un comentario permanentemente
     */
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}