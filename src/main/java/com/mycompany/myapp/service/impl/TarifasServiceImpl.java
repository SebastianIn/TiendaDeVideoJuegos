package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TarifasService;
import com.mycompany.myapp.domain.Tarifas;
import com.mycompany.myapp.repository.TarifasRepository;
import com.mycompany.myapp.service.dto.TarifasDTO;
import com.mycompany.myapp.service.mapper.TarifasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tarifas}.
 */
@Service
@Transactional
public class TarifasServiceImpl implements TarifasService {

    private final Logger log = LoggerFactory.getLogger(TarifasServiceImpl.class);

    private final TarifasRepository tarifasRepository;

    private final TarifasMapper tarifasMapper;

    public TarifasServiceImpl(TarifasRepository tarifasRepository, TarifasMapper tarifasMapper) {
        this.tarifasRepository = tarifasRepository;
        this.tarifasMapper = tarifasMapper;
    }

    @Override
    public TarifasDTO save(TarifasDTO tarifasDTO) {
        log.debug("Request to save Tarifas : {}", tarifasDTO);
        Tarifas tarifas = tarifasMapper.toEntity(tarifasDTO);
        tarifas = tarifasRepository.save(tarifas);
        return tarifasMapper.toDto(tarifas);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TarifasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tarifas");
        return tarifasRepository.findAll(pageable)
            .map(tarifasMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TarifasDTO> findOne(Long id) {
        log.debug("Request to get Tarifas : {}", id);
        return tarifasRepository.findById(id)
            .map(tarifasMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tarifas : {}", id);
        tarifasRepository.deleteById(id);
    }
}
