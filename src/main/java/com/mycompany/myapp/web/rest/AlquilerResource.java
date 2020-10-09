package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.AlquilerService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.AlquilerDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Alquiler}.
 */
@RestController
@RequestMapping("/api")
public class AlquilerResource {

    private final Logger log = LoggerFactory.getLogger(AlquilerResource.class);

    private static final String ENTITY_NAME = "alquiler";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlquilerService alquilerService;

    public AlquilerResource(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    /**
     * {@code POST  /alquilers} : Create a new alquiler.
     *
     * @param alquilerDTO the alquilerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alquilerDTO, or with status {@code 400 (Bad Request)} if the alquiler has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alquilers")
    public ResponseEntity<AlquilerDTO> createAlquiler(@RequestBody AlquilerDTO alquilerDTO) throws URISyntaxException {
        log.debug("REST request to save Alquiler : {}", alquilerDTO);
        if (alquilerDTO.getId() != null) {
            throw new BadRequestAlertException("A new alquiler cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlquilerDTO result = alquilerService.save(alquilerDTO);
        return ResponseEntity.created(new URI("/api/alquilers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alquilers} : Updates an existing alquiler.
     *
     * @param alquilerDTO the alquilerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alquilerDTO,
     * or with status {@code 400 (Bad Request)} if the alquilerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alquilerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alquilers")
    public ResponseEntity<AlquilerDTO> updateAlquiler(@RequestBody AlquilerDTO alquilerDTO) throws URISyntaxException {
        log.debug("REST request to update Alquiler : {}", alquilerDTO);
        if (alquilerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AlquilerDTO result = alquilerService.save(alquilerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alquilerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /alquilers} : get all the alquilers.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alquilers in body.
     */
    @GetMapping("/alquilers")
    public ResponseEntity<List<AlquilerDTO>> getAllAlquilers(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Alquilers");
        Page<AlquilerDTO> page;
        if (eagerload) {
            page = alquilerService.findAllWithEagerRelationships(pageable);
        } else {
            page = alquilerService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alquilers/:id} : get the "id" alquiler.
     *
     * @param id the id of the alquilerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alquilerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alquilers/{id}")
    public ResponseEntity<AlquilerDTO> getAlquiler(@PathVariable Long id) {
        log.debug("REST request to get Alquiler : {}", id);
        Optional<AlquilerDTO> alquilerDTO = alquilerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alquilerDTO);
    }

    /**
     * {@code DELETE  /alquilers/:id} : delete the "id" alquiler.
     *
     * @param id the id of the alquilerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alquilers/{id}")
    public ResponseEntity<Void> deleteAlquiler(@PathVariable Long id) {
        log.debug("REST request to delete Alquiler : {}", id);
        alquilerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
