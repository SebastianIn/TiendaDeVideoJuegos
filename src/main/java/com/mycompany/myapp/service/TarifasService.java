package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TarifasDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Tarifas}.
 */
public interface TarifasService {

    /**
     * Save a tarifas.
     *
     * @param tarifasDTO the entity to save.
     * @return the persisted entity.
     */
    TarifasDTO save(TarifasDTO tarifasDTO);

    /**
     * Get all the tarifas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TarifasDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tarifas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TarifasDTO> findOne(Long id);

    /**
     * Delete the "id" tarifas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
