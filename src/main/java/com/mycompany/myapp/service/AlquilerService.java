package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AlquilerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Alquiler}.
 */
public interface AlquilerService {

    /**
     * Save a alquiler.
     *
     * @param alquilerDTO the entity to save.
     * @return the persisted entity.
     */
    AlquilerDTO save(AlquilerDTO alquilerDTO);

    /**
     * Get all the alquilers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AlquilerDTO> findAll(Pageable pageable);

    /**
     * Get all the alquilers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<AlquilerDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" alquiler.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AlquilerDTO> findOne(Long id);

    /**
     * Delete the "id" alquiler.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
