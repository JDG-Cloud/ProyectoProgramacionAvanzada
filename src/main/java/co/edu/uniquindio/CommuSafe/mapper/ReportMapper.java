package co.edu.uniquindio.CommuSafe.mapper;

import co.edu.uniquindio.CommuSafe.dto.report.CreateReportDTO;
import co.edu.uniquindio.CommuSafe.modelo.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel ="spring")
public interface ReportMapper {

    @Mapping(target = "Rol", constant="CLIENTE")
    @Mapping (target = "UserStatus", constant ="INACTIVO")
    @Mapping (target = "dateRegister", expression="java(java.time.LocalDateTime.now())")

    Report toDocument (CreateReportDTO reportDTO);
    
}
