package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TarifasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tarifas} and its DTO {@link TarifasDTO}.
 */
@Mapper(componentModel = "spring", uses = {JuegoMapper.class})
public interface TarifasMapper extends EntityMapper<TarifasDTO, Tarifas> {

    @Mapping(source = "juego.id", target = "juegoId")
    @Mapping(source = "juego.nombre", target = "juegoNombre")
    TarifasDTO toDto(Tarifas tarifas);

    @Mapping(source = "juegoId", target = "juego")
    Tarifas toEntity(TarifasDTO tarifasDTO);

    default Tarifas fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tarifas tarifas = new Tarifas();
        tarifas.setId(id);
        return tarifas;
    }
}
