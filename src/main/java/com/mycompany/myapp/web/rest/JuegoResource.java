package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.JuegoService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.JuegoDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Juego}.
 */
@RestController
@RequestMapping("/api")
public class JuegoResource {

    private final Logger log = LoggerFactory.getLogger(JuegoResource.class);

    private static final String ENTITY_NAME = "juego";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JuegoService juegoService;

    public JuegoResource(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    /**
     * {@code POST  /juegos} : Create a new juego.
     *
     * @param juegoDTO the juegoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new juegoDTO, or with status {@code 400 (Bad Request)} if the juego has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/juegos")
    public ResponseEntity<JuegoDTO> createJuego(@RequestBody JuegoDTO juegoDTO) throws URISyntaxException {
        log.debug("REST request to save Juego : {}", juegoDTO);
        if (juegoDTO.getId() != null) {
            throw new BadRequestAlertException("A new juego cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JuegoDTO result = juegoService.save(juegoDTO);
        return ResponseEntity.created(new URI("/api/juegos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /juegos} : Updates an existing juego.
     *
     * @param juegoDTO the juegoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated juegoDTO,
     * or with status {@code 400 (Bad Request)} if the juegoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the juegoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/juegos")
    public ResponseEntity<JuegoDTO> updateJuego(@RequestBody JuegoDTO juegoDTO) throws URISyntaxException {
        log.debug("REST request to update Juego : {}", juegoDTO);
        if (juegoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JuegoDTO result = juegoService.save(juegoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, juegoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /juegos} : get all the juegos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of juegos in body.
     */
    @GetMapping("/juegos")
    public ResponseEntity<List<JuegoDTO>> getAllJuegos(Pageable pageable) {
        log.debug("REST request to get a page of Juegos");
        Page<JuegoDTO> page = juegoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /juegos/:id} : get the "id" juego.
     *
     * @param id the id of the juegoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the juegoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/juegos/{id}")
    public ResponseEntity<JuegoDTO> getJuego(@PathVariable Long id) {
        log.debug("REST request to get Juego : {}", id);
        Optional<JuegoDTO> juegoDTO = juegoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(juegoDTO);
    }

    /**
     * {@code DELETE  /juegos/:id} : delete the "id" juego.
     *
     * @param id the id of the juegoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/juegos/{id}")
    public ResponseEntity<Void> deleteJuego(@PathVariable Long id) {
        log.debug("REST request to delete Juego : {}", id);
        juegoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
