package co.edu.uniquindio.CommuSafe.modules.util;

import co.edu.uniquindio.CommuSafe.modules.report.dto.LocationDto;
import co.edu.uniquindio.CommuSafe.modules.report.model.Location;
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
            throw new IllegalArgumentException(String.format("value '%s' not is a valid object parameter", id));
        }
        return new ObjectId(id);
    }

    @Named("locationDtoToLocation")
    public Location locationDtoToLocation(LocationDto locationDto) {
        if (locationDto == null) {
            return null;
        }
        Location location = new Location();
        location.setLatitude(locationDto.latitude());
        location.setLongitude(locationDto.longitude());
        return location;
    }
}

