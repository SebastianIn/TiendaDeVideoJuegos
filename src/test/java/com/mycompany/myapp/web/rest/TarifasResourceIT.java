package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TIendaDeVideoJuegosApp;
import com.mycompany.myapp.domain.Tarifas;
import com.mycompany.myapp.repository.TarifasRepository;
import com.mycompany.myapp.service.TarifasService;
import com.mycompany.myapp.service.dto.TarifasDTO;
import com.mycompany.myapp.service.mapper.TarifasMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TarifasResource} REST controller.
 */
@SpringBootTest(classes = TIendaDeVideoJuegosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TarifasResourceIT {

    private static final BigDecimal DEFAULT_PRECIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECIO = new BigDecimal(2);

    @Autowired
    private TarifasRepository tarifasRepository;

    @Autowired
    private TarifasMapper tarifasMapper;

    @Autowired
    private TarifasService tarifasService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTarifasMockMvc;

    private Tarifas tarifas;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tarifas createEntity(EntityManager em) {
        Tarifas tarifas = new Tarifas()
            .precio(DEFAULT_PRECIO);
        return tarifas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tarifas createUpdatedEntity(EntityManager em) {
        Tarifas tarifas = new Tarifas()
            .precio(UPDATED_PRECIO);
        return tarifas;
    }

    @BeforeEach
    public void initTest() {
        tarifas = createEntity(em);
    }

    @Test
    @Transactional
    public void createTarifas() throws Exception {
        int databaseSizeBeforeCreate = tarifasRepository.findAll().size();
        // Create the Tarifas
        TarifasDTO tarifasDTO = tarifasMapper.toDto(tarifas);
        restTarifasMockMvc.perform(post("/api/tarifas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tarifasDTO)))
            .andExpect(status().isCreated());

        // Validate the Tarifas in the database
        List<Tarifas> tarifasList = tarifasRepository.findAll();
        assertThat(tarifasList).hasSize(databaseSizeBeforeCreate + 1);
        Tarifas testTarifas = tarifasList.get(tarifasList.size() - 1);
        assertThat(testTarifas.getPrecio()).isEqualTo(DEFAULT_PRECIO);
    }

    @Test
    @Transactional
    public void createTarifasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tarifasRepository.findAll().size();

        // Create the Tarifas with an existing ID
        tarifas.setId(1L);
        TarifasDTO tarifasDTO = tarifasMapper.toDto(tarifas);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTarifasMockMvc.perform(post("/api/tarifas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tarifasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tarifas in the database
        List<Tarifas> tarifasList = tarifasRepository.findAll();
        assertThat(tarifasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTarifas() throws Exception {
        // Initialize the database
        tarifasRepository.saveAndFlush(tarifas);

        // Get all the tarifasList
        restTarifasMockMvc.perform(get("/api/tarifas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tarifas.getId().intValue())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.intValue())));
    }
    
    @Test
    @Transactional
    public void getTarifas() throws Exception {
        // Initialize the database
        tarifasRepository.saveAndFlush(tarifas);

        // Get the tarifas
        restTarifasMockMvc.perform(get("/api/tarifas/{id}", tarifas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tarifas.getId().intValue()))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTarifas() throws Exception {
        // Get the tarifas
        restTarifasMockMvc.perform(get("/api/tarifas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTarifas() throws Exception {
        // Initialize the database
        tarifasRepository.saveAndFlush(tarifas);

        int databaseSizeBeforeUpdate = tarifasRepository.findAll().size();

        // Update the tarifas
        Tarifas updatedTarifas = tarifasRepository.findById(tarifas.getId()).get();
        // Disconnect from session so that the updates on updatedTarifas are not directly saved in db
        em.detach(updatedTarifas);
        updatedTarifas
            .precio(UPDATED_PRECIO);
        TarifasDTO tarifasDTO = tarifasMapper.toDto(updatedTarifas);

        restTarifasMockMvc.perform(put("/api/tarifas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tarifasDTO)))
            .andExpect(status().isOk());

        // Validate the Tarifas in the database
        List<Tarifas> tarifasList = tarifasRepository.findAll();
        assertThat(tarifasList).hasSize(databaseSizeBeforeUpdate);
        Tarifas testTarifas = tarifasList.get(tarifasList.size() - 1);
        assertThat(testTarifas.getPrecio()).isEqualTo(UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void updateNonExistingTarifas() throws Exception {
        int databaseSizeBeforeUpdate = tarifasRepository.findAll().size();

        // Create the Tarifas
        TarifasDTO tarifasDTO = tarifasMapper.toDto(tarifas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTarifasMockMvc.perform(put("/api/tarifas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tarifasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tarifas in the database
        List<Tarifas> tarifasList = tarifasRepository.findAll();
        assertThat(tarifasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTarifas() throws Exception {
        // Initialize the database
        tarifasRepository.saveAndFlush(tarifas);

        int databaseSizeBeforeDelete = tarifasRepository.findAll().size();

        // Delete the tarifas
        restTarifasMockMvc.perform(delete("/api/tarifas/{id}", tarifas.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tarifas> tarifasList = tarifasRepository.findAll();
        assertThat(tarifasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
