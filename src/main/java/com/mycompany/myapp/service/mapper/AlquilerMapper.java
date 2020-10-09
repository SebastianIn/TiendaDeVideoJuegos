package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AlquilerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Alquiler} and its DTO {@link AlquilerDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClienteMapper.class, JuegoMapper.class})
public interface AlquilerMapper extends EntityMapper<AlquilerDTO, Alquiler> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cedula", target = "clienteCedula")
    AlquilerDTO toDto(Alquiler alquiler);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(target = "removeJuegos", ignore = true)
    Alquiler toEntity(AlquilerDTO alquilerDTO);

    default Alquiler fromId(Long id) {
        if (id == null) {
            return null;
        }
        Alquiler alquiler = new Alquiler();
        alquiler.setId(id);
        return alquiler;
    }
}
