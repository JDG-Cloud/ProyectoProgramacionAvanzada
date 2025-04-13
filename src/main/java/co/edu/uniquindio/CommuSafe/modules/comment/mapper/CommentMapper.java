package co.edu.uniquindio.CommuSafe.modules.comment.mapper;

import co.edu.uniquindio.CommuSafe.modules.util.ObjectIdMapperUtil;
import co.edu.uniquindio.CommuSafe.modules.comment.dto.*;
import co.edu.uniquindio.CommuSafe.modules.comment.model.Comment;
import co.edu.uniquindio.CommuSafe.modules.comment.dto.CommentModificationResponseDTO;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", uses = {ObjectIdMapperUtil.class})
public interface CommentMapper {

    @Mapping(target = "message", source="message")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "deleted", constant = "false")
    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "stringToObjectId")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "stringToObjectId")
    Comment toEntity(CommentCreationRequestDTO dto);

    @Mapping(target = "message", source="message")
    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "objectIdToString")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "objectIdToString")
    CommentCreationResponseDTO toCreationResponseDTO(Comment comment);

    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "objectIdToString")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "objectIdToString")
    CommentDTO toDTO(Comment comment);

    List<CommentDTO> toDTOList(List<Comment> comments);

    @Mapping(source = "id", target = "id", qualifiedByName = "stringToObjectId")
    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "stringToObjectId")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "stringToObjectId")
    Comment updateEntityFromModificationRequest(CommentModificationRequestDTO dto);

    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "objectIdToString")
    CommentModificationResponseDTO toModificationResponseDTO(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "id", target = "id", qualifiedByName = "stringToObjectId")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "stringToObjectId")
    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "stringToObjectId")
    void updateCommentFromDTO(CommentModificationRequestDTO dto, @MappingTarget Comment comment);
}