package co.edu.uniquindio.CommuSafe.modules.comment.repository;

import co.edu.uniquindio.CommuSafe.modules.comment.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.bson.types.ObjectId;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String>{

    List<Comment> findByUserId(ObjectId userId);

    List<Comment> findByReportId(ObjectId reportId);

    @Query("{'reportId' :  ?0, 'deleted' : false}")
    List<Comment> findActiveCommentByReportId(ObjectId reportId);

    @Query("{'userId' :  ?0, 'deleted' : false}")
    List<Comment> findActiveCommentByUserId(ObjectId userId);

    @Query("{ 'reportId' : ?0 }")
    List<Comment> findAllByReportIdOrderByDateDesc(ObjectId reportId);

    long countByReportId(ObjectId reportId);

    long countByReportIdAndDeleted(ObjectId reportId, boolean deleted);

}
