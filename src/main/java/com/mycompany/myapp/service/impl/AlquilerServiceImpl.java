package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AlquilerService;
import com.mycompany.myapp.domain.Alquiler;
import com.mycompany.myapp.repository.AlquilerRepository;
import com.mycompany.myapp.service.dto.AlquilerDTO;
import com.mycompany.myapp.service.mapper.AlquilerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Alquiler}.
 */
@Service
@Transactional
public class AlquilerServiceImpl implements AlquilerService {

    private final Logger log = LoggerFactory.getLogger(AlquilerServiceImpl.class);

    private final AlquilerRepository alquilerRepository;

    private final AlquilerMapper alquilerMapper;

    public AlquilerServiceImpl(AlquilerRepository alquilerRepository, AlquilerMapper alquilerMapper) {
        this.alquilerRepository = alquilerRepository;
        this.alquilerMapper = alquilerMapper;
    }

    @Override
    public AlquilerDTO save(AlquilerDTO alquilerDTO) {
        log.debug("Request to save Alquiler : {}", alquilerDTO);
        Alquiler alquiler = alquilerMapper.toEntity(alquilerDTO);
        alquiler = alquilerRepository.save(alquiler);
        return alquilerMapper.toDto(alquiler);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlquilerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Alquilers");
        return alquilerRepository.findAll(pageable)
            .map(alquilerMapper::toDto);
    }


    public Page<AlquilerDTO> findAllWithEagerRelationships(Pageable pageable) {
        return alquilerRepository.findAllWithEagerRelationships(pageable).map(alquilerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlquilerDTO> findOne(Long id) {
        log.debug("Request to get Alquiler : {}", id);
        return alquilerRepository.findOneWithEagerRelationships(id)
            .map(alquilerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Alquiler : {}", id);
        alquilerRepository.deleteById(id);
    }
}
