package co.edu.uniquindio.CommuSafe.modules.comment.controller;

import co.edu.uniquindio.CommuSafe.modules.comment.dto.*;
import co.edu.uniquindio.CommuSafe.modules.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Obtiene comentarios por ID de reporte
     * Utiliza el patrón de ruta anidada del fragmento
     */
    @GetMapping("{reportId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByReportId(@PathVariable String reportId) {
        return ResponseEntity.ok(commentService.getCommentsByReportId(reportId));
    }

    /**
     * Crea un nuevo comentario para un reporte
     * Utiliza el patrón de ruta anidada del fragmento
     */
    @PostMapping
    public ResponseEntity<CommentCreationResponseDTO> createComment(
            @Valid @RequestBody CommentCreationRequestDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    /**
     * Cuenta los comentarios por ID de reporte
     */
    @GetMapping("counter/{reportId}")
    public ResponseEntity<Long> countCommentsByReportId(@PathVariable String reportId) {
        return ResponseEntity.ok(commentService.countCommentsByReportId(reportId));
    }

    /**
     * Actualiza un comentario existente
     */
    @PutMapping
    public ResponseEntity<CommentModificationResponseDTO> updateComment(
            @Valid @RequestBody CommentModificationRequestDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(commentDTO));
    }

    /**
     * Eliminación suave de un comentario (marcarlo como eliminado)
     */
    @DeleteMapping("{id}")
    public ResponseEntity<CommentDTO> softDeleteComment(@PathVariable String id) {
        return ResponseEntity.ok(commentService.softDeleteComment(id));
    }
}