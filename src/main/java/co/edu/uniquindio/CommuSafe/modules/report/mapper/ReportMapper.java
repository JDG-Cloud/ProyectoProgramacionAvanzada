package co.edu.uniquindio.CommuSafe.modules.report.mapper;

import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportCreationRequestDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportDto;
import co.edu.uniquindio.CommuSafe.modules.report.dto.ReportModificationRequestDto;
import co.edu.uniquindio.CommuSafe.modules.report.model.Report;
import co.edu.uniquindio.CommuSafe.modules.util.ObjectIdMapperUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = ObjectIdMapperUtil.class)
public interface ReportMapper {
    Report toEntity(ReportDto dto);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "location", target = "location", qualifiedByName = "locationDtoToLocation")
    @Mapping(source = "category", target = "category", qualifiedByName = "stringToObjectId")
    @Mapping(source = "client", target = "client", qualifiedByName = "stringToObjectId")
    Report toEntity(ReportCreationRequestDto dto);


    @Mapping(source = "id", target = "id", qualifiedByName = "stringToObjectId")
    @Mapping(source = "category", target = "category", qualifiedByName = "stringToObjectId")
    @Mapping(source = "client", target = "client", qualifiedByName = "stringToObjectId")
    Report toEntity(ReportModificationRequestDto dto);

    ReportDto toDTOList(Report report);
}
