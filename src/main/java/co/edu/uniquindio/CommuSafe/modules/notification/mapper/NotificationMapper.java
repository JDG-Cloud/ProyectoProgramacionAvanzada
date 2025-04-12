package co.edu.uniquindio.CommuSafe.modules.notification.mapper;

import co.edu.uniquindio.CommuSafe.modules.util.ObjectIdMapperUtil;
import co.edu.uniquindio.CommuSafe.modules.notification.dto.*;
import co.edu.uniquindio.CommuSafe.modules.notification.model.Notification;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapperUtil.class)
public interface NotificationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "read", constant = "false")
    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "stringToObjectId")
    @Mapping(source = "receiver", target = "receiver", qualifiedByName = "stringToObjectId")
    Notification toEntity(CreateNotificationRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "reportId", target = "reportId")
    @Mapping(source = "receiver", target = "receiver")
    CreateNotificationResponseDTO toResponseDTO(Notification notification);

    List<CreateNotificationResponseDTO> toListResponseDTO(List<Notification> list);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "reportId", target = "reportId")
    @Mapping(source = "receiver", target = "receiver")
    NotificationDTO toDTO(Notification notification);

    List<NotificationDTO> toDTOList(List<Notification> notifications);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNotificationFromDTO(UpdateNotificationDTO dto, @MappingTarget Notification notification);
}
