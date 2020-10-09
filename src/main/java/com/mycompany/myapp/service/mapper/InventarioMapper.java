package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.InventarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Inventario} and its DTO {@link InventarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {JuegoMapper.class})
public interface InventarioMapper extends EntityMapper<InventarioDTO, Inventario> {

    @Mapping(source = "juego.id", target = "juegoId")
    @Mapping(source = "juego.nombre", target = "juegoNombre")
    InventarioDTO toDto(Inventario inventario);

    @Mapping(source = "juegoId", target = "juego")
    Inventario toEntity(InventarioDTO inventarioDTO);

    default Inventario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Inventario inventario = new Inventario();
        inventario.setId(id);
        return inventario;
    }
}
