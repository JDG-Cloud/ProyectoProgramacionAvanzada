package co.edu.uniquindio.CommuSafe.modules.util;

import org.bson.types.ObjectId;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class ObjectIdMapperUtil {

    @Named("objectIdToString")
    public String objectIdToString(ObjectId id) {
        return id != null ? id.toHexString() : null;
    }

    @Named("stringToObjectId")
    public ObjectId stringToObjectId(String id) {
        if (id == null || !ObjectId.isValid(id)) {
            throw new IllegalArgumentException("ID de categoría no válido");
        }
        return new ObjectId(id);
    }
}