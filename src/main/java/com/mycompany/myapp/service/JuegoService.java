package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.JuegoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Juego}.
 */
public interface JuegoService {

    /**
     * Save a juego.
     *
     * @param juegoDTO the entity to save.
     * @return the persisted entity.
     */
    JuegoDTO save(JuegoDTO juegoDTO);

    /**
     * Get all the juegos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JuegoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" juego.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JuegoDTO> findOne(Long id);

    /**
     * Delete the "id" juego.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
