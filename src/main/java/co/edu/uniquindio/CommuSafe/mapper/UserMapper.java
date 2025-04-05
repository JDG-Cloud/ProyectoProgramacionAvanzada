package co.edu.uniquindio.CommuSafe.mapper;

import co.edu.uniquindio.CommuSafe.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.CommuSafe.dto.usuarios.UsuarioDTO;
import co.edu.uniquindio.CommuSafe.modelo.User;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public interface UserMapper {

    @Mapping (target = "Rol", constant="CLIENTE")
    @Mapping (target = "UserStatus", constant ="INACTIVO")
    @Mapping (target = "dateRegister", expression="java(java.time.LocalDateTime.now())")
    User toDocument(CrearUsuarioDTO userDTO);

    UsuarioDTO toDTO(User user);

    //metodo que su usa para mapear ObjectId a String
    default String map (ObjectId value){
        return value != null ? value.toString() : null;
  }
}
