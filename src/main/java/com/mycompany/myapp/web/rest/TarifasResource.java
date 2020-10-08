package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.TarifasService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.TarifasDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Tarifas}.
 */
@RestController
@RequestMapping("/api")
public class TarifasResource {

    private final Logger log = LoggerFactory.getLogger(TarifasResource.class);

    private static final String ENTITY_NAME = "tarifas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TarifasService tarifasService;

    public TarifasResource(TarifasService tarifasService) {
        this.tarifasService = tarifasService;
    }

    /**
     * {@code POST  /tarifas} : Create a new tarifas.
     *
     * @param tarifasDTO the tarifasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tarifasDTO, or with status {@code 400 (Bad Request)} if the tarifas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tarifas")
    public ResponseEntity<TarifasDTO> createTarifas(@RequestBody TarifasDTO tarifasDTO) throws URISyntaxException {
        log.debug("REST request to save Tarifas : {}", tarifasDTO);
        if (tarifasDTO.getId() != null) {
            throw new BadRequestAlertException("A new tarifas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TarifasDTO result = tarifasService.save(tarifasDTO);
        return ResponseEntity.created(new URI("/api/tarifas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tarifas} : Updates an existing tarifas.
     *
     * @param tarifasDTO the tarifasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tarifasDTO,
     * or with status {@code 400 (Bad Request)} if the tarifasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tarifasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tarifas")
    public ResponseEntity<TarifasDTO> updateTarifas(@RequestBody TarifasDTO tarifasDTO) throws URISyntaxException {
        log.debug("REST request to update Tarifas : {}", tarifasDTO);
        if (tarifasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TarifasDTO result = tarifasService.save(tarifasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tarifasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tarifas} : get all the tarifas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tarifas in body.
     */
    @GetMapping("/tarifas")
    public ResponseEntity<List<TarifasDTO>> getAllTarifas(Pageable pageable) {
        log.debug("REST request to get a page of Tarifas");
        Page<TarifasDTO> page = tarifasService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tarifas/:id} : get the "id" tarifas.
     *
     * @param id the id of the tarifasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tarifasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tarifas/{id}")
    public ResponseEntity<TarifasDTO> getTarifas(@PathVariable Long id) {
        log.debug("REST request to get Tarifas : {}", id);
        Optional<TarifasDTO> tarifasDTO = tarifasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tarifasDTO);
    }

    /**
     * {@code DELETE  /tarifas/:id} : delete the "id" tarifas.
     *
     * @param id the id of the tarifasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tarifas/{id}")
    public ResponseEntity<Void> deleteTarifas(@PathVariable Long id) {
        log.debug("REST request to delete Tarifas : {}", id);
        tarifasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
