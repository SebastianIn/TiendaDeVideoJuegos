package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.JuegoService;
import com.mycompany.myapp.domain.Juego;
import com.mycompany.myapp.repository.JuegoRepository;
import com.mycompany.myapp.service.dto.JuegoDTO;
import com.mycompany.myapp.service.mapper.JuegoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Juego}.
 */
@Service
@Transactional
public class JuegoServiceImpl implements JuegoService {

    private final Logger log = LoggerFactory.getLogger(JuegoServiceImpl.class);

    private final JuegoRepository juegoRepository;

    private final JuegoMapper juegoMapper;

    public JuegoServiceImpl(JuegoRepository juegoRepository, JuegoMapper juegoMapper) {
        this.juegoRepository = juegoRepository;
        this.juegoMapper = juegoMapper;
    }

    @Override
    public JuegoDTO save(JuegoDTO juegoDTO) {
        log.debug("Request to save Juego : {}", juegoDTO);
        Juego juego = juegoMapper.toEntity(juegoDTO);
        juego = juegoRepository.save(juego);
        return juegoMapper.toDto(juego);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JuegoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Juegos");
        return juegoRepository.findAll(pageable)
            .map(juegoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<JuegoDTO> findOne(Long id) {
        log.debug("Request to get Juego : {}", id);
        return juegoRepository.findById(id)
            .map(juegoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Juego : {}", id);
        juegoRepository.deleteById(id);
    }
}
