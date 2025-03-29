package co.edu.uniquindio.CommuSafe.repositorios;

import co.edu.uniquindio.CommuSafe.modelo.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends MongoRepository <Comment, ObjectId>{

}
