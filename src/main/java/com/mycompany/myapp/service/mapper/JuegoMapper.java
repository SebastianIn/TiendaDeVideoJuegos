package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.JuegoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Juego} and its DTO {@link JuegoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JuegoMapper extends EntityMapper<JuegoDTO, Juego> {



    default Juego fromId(Long id) {
        if (id == null) {
            return null;
        }
        Juego juego = new Juego();
        juego.setId(id);
        return juego;
    }
}
