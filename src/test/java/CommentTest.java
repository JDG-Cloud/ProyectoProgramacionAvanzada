
import co.edu.uniquindio.CommuSafe.modules.comment.model.Comment;
import co.edu.uniquindio.CommuSafe.modules.comment.repository.CommentRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentTest {

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        commentRepository.deleteAll();
    }

    @Test
    public void createCommentTest() {
        Comment comment = new Comment();
        comment.setMessage("Este es un comentario de prueba");
        comment.setReportId(new ObjectId());
        comment.setUserId(new ObjectId());
        comment.setCreatedBy("usuarioPrueba");
        comment.setScore(5);
        comment.setDate(LocalDateTime.now());
        comment.setDeleted(false);

        Comment savedComment = commentRepository.save(comment);

        assertNotNull(savedComment);
        assertNotNull(savedComment.getId());

        System.out.println("Comentario creado con ID: " + savedComment.getId());
    }

    @Test
    public void updateCommentTest() {
        Comment comment = new Comment();
        comment.setMessage("Texto inicial del comentario");
        comment.setReportId(new ObjectId());
        comment.setUserId(new ObjectId());
        comment.setCreatedBy("usuarioPrueba");
        comment.setScore(3);
        comment.setDate(LocalDateTime.now());
        comment.setDeleted(false);

        Comment savedComment = commentRepository.save(comment);
        String commentId = savedComment.getId().toString();

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        assertTrue(optionalComment.isPresent(), "El comentario debe existir para actualizarlo.");

        Comment commentToUpdate = optionalComment.get();
        commentToUpdate.setMessage("Texto actualizado del comentario");
        commentToUpdate.setScore(4);

        commentRepository.save(commentToUpdate);

        Comment updatedComment = commentRepository.findById(commentId).orElseThrow();
        assertEquals("Texto actualizado del comentario", updatedComment.getMessage(), "El mensaje del comentario debería estar actualizado.");
        assertEquals(4, updatedComment.getScore(), "La puntuación del comentario debería estar actualizada.");
    }

    @Test
    public void listCommentsByReportTest() {
        ObjectId reportId = new ObjectId();

        Comment comment1 = new Comment();
        comment1.setMessage("Comentario 1 para el reporte");
        comment1.setReportId(reportId);
        comment1.setUserId(new ObjectId());
        comment1.setCreatedBy("usuario1");
        comment1.setScore(4);
        comment1.setDate(LocalDateTime.now());
        comment1.setDeleted(false);

        Comment comment2 = new Comment();
        comment2.setMessage("Comentario 2 para el reporte");
        comment2.setReportId(reportId);
        comment2.setUserId(new ObjectId());
        comment2.setCreatedBy("usuario2");
        comment2.setScore(5);
        comment2.setDate(LocalDateTime.now());
        comment2.setDeleted(false);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<Comment> reportComments = commentRepository.findByReportId(reportId);

        assertFalse(reportComments.isEmpty(), "La lista de comentarios del reporte no debería estar vacía.");
        assertEquals(2, reportComments.size(), "Debería haber dos comentarios para este reporte.");
    }

    @Test
    public void softDeleteCommentTest() {
        Comment comment = new Comment();
        comment.setMessage("Comentario a eliminar suavemente");
        comment.setReportId(new ObjectId());
        comment.setUserId(new ObjectId());
        comment.setCreatedBy("usuarioPrueba");
        comment.setScore(2);
        comment.setDate(LocalDateTime.now());
        comment.setDeleted(false);

        Comment savedComment = commentRepository.save(comment);
        String commentId = savedComment.getId().toString();

        Comment commentToSoftDelete = commentRepository.findById(commentId).orElseThrow();
        commentToSoftDelete.setDeleted(true);
        commentRepository.save(commentToSoftDelete);

        Comment softDeletedComment = commentRepository.findById(commentId).orElseThrow();
        assertTrue(softDeletedComment.isDeleted(), "El comentario debería estar marcado como eliminado.");
    }

    @Test
    public void hardDeleteCommentTest() {
        Comment comment = new Comment();
        comment.setMessage("Comentario a eliminar permanentemente");
        comment.setReportId(new ObjectId());
        comment.setUserId(new ObjectId());
        comment.setCreatedBy("usuarioPrueba");
        comment.setScore(1);
        comment.setDate(LocalDateTime.now());
        comment.setDeleted(false);

        Comment savedComment = commentRepository.save(comment);
        String commentId = savedComment.getId().toString();

        commentRepository.deleteById(commentId);

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        assertTrue(optionalComment.isEmpty(), "El comentario debería haber sido eliminado.");
    }

    @Test
    public void countCommentsByReportTest() {
        ObjectId reportId = new ObjectId();

        Comment comment1 = new Comment();
        comment1.setMessage("Primer comentario para prueba de conteo");
        comment1.setReportId(reportId);
        comment1.setUserId(new ObjectId());
        comment1.setCreatedBy("usuario1");
        comment1.setScore(3);
        comment1.setDate(LocalDateTime.now());
        comment1.setDeleted(false);

        Comment comment2 = new Comment();
        comment2.setMessage("Segundo comentario para prueba de conteo");
        comment2.setReportId(reportId);
        comment2.setUserId(new ObjectId());
        comment2.setCreatedBy("usuario2");
        comment2.setScore(4);
        comment2.setDate(LocalDateTime.now());
        comment2.setDeleted(false);

        Comment comment3 = new Comment();
        comment3.setMessage("Tercer comentario para prueba de conteo - eliminado");
        comment3.setReportId(reportId);
        comment3.setUserId(new ObjectId());
        comment3.setCreatedBy("usuario3");
        comment3.setScore(2);
        comment3.setDate(LocalDateTime.now());
        comment3.setDeleted(true);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        long totalCount = commentRepository.countByReportId(reportId);
        long activeCount = commentRepository.countByReportIdAndDeleted(reportId, false);

        assertEquals(3, totalCount, "El recuento total debería ser de 3 comentarios.");
        assertEquals(2, activeCount, "El recuento de activos debería ser de 2 comentarios (excluyendo eliminados).");
    }
}