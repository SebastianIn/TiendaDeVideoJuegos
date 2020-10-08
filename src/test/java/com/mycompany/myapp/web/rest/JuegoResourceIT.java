package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TIendaDeVideoJuegosApp;
import com.mycompany.myapp.domain.Juego;
import com.mycompany.myapp.repository.JuegoRepository;
import com.mycompany.myapp.service.JuegoService;
import com.mycompany.myapp.service.dto.JuegoDTO;
import com.mycompany.myapp.service.mapper.JuegoMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link JuegoResource} REST controller.
 */
@SpringBootTest(classes = TIendaDeVideoJuegosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class JuegoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_PUBLICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_PUBLICACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PROTAGONISTAS = "AAAAAAAAAA";
    private static final String UPDATED_PROTAGONISTAS = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECTOR = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTOR = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTOR = "BBBBBBBBBB";

    private static final String DEFAULT_TECNOLOGIA = "AAAAAAAAAA";
    private static final String UPDATED_TECNOLOGIA = "BBBBBBBBBB";

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private JuegoMapper juegoMapper;

    @Autowired
    private JuegoService juegoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJuegoMockMvc;

    private Juego juego;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Juego createEntity(EntityManager em) {
        Juego juego = new Juego()
            .nombre(DEFAULT_NOMBRE)
            .fechaPublicacion(DEFAULT_FECHA_PUBLICACION)
            .protagonistas(DEFAULT_PROTAGONISTAS)
            .director(DEFAULT_DIRECTOR)
            .productor(DEFAULT_PRODUCTOR)
            .tecnologia(DEFAULT_TECNOLOGIA);
        return juego;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Juego createUpdatedEntity(EntityManager em) {
        Juego juego = new Juego()
            .nombre(UPDATED_NOMBRE)
            .fechaPublicacion(UPDATED_FECHA_PUBLICACION)
            .protagonistas(UPDATED_PROTAGONISTAS)
            .director(UPDATED_DIRECTOR)
            .productor(UPDATED_PRODUCTOR)
            .tecnologia(UPDATED_TECNOLOGIA);
        return juego;
    }

    @BeforeEach
    public void initTest() {
        juego = createEntity(em);
    }

    @Test
    @Transactional
    public void createJuego() throws Exception {
        int databaseSizeBeforeCreate = juegoRepository.findAll().size();
        // Create the Juego
        JuegoDTO juegoDTO = juegoMapper.toDto(juego);
        restJuegoMockMvc.perform(post("/api/juegos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juegoDTO)))
            .andExpect(status().isCreated());

        // Validate the Juego in the database
        List<Juego> juegoList = juegoRepository.findAll();
        assertThat(juegoList).hasSize(databaseSizeBeforeCreate + 1);
        Juego testJuego = juegoList.get(juegoList.size() - 1);
        assertThat(testJuego.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testJuego.getFechaPublicacion()).isEqualTo(DEFAULT_FECHA_PUBLICACION);
        assertThat(testJuego.getProtagonistas()).isEqualTo(DEFAULT_PROTAGONISTAS);
        assertThat(testJuego.getDirector()).isEqualTo(DEFAULT_DIRECTOR);
        assertThat(testJuego.getProductor()).isEqualTo(DEFAULT_PRODUCTOR);
        assertThat(testJuego.getTecnologia()).isEqualTo(DEFAULT_TECNOLOGIA);
    }

    @Test
    @Transactional
    public void createJuegoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = juegoRepository.findAll().size();

        // Create the Juego with an existing ID
        juego.setId(1L);
        JuegoDTO juegoDTO = juegoMapper.toDto(juego);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJuegoMockMvc.perform(post("/api/juegos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juegoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Juego in the database
        List<Juego> juegoList = juegoRepository.findAll();
        assertThat(juegoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllJuegos() throws Exception {
        // Initialize the database
        juegoRepository.saveAndFlush(juego);

        // Get all the juegoList
        restJuegoMockMvc.perform(get("/api/juegos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(juego.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].fechaPublicacion").value(hasItem(DEFAULT_FECHA_PUBLICACION.toString())))
            .andExpect(jsonPath("$.[*].protagonistas").value(hasItem(DEFAULT_PROTAGONISTAS)))
            .andExpect(jsonPath("$.[*].director").value(hasItem(DEFAULT_DIRECTOR)))
            .andExpect(jsonPath("$.[*].productor").value(hasItem(DEFAULT_PRODUCTOR)))
            .andExpect(jsonPath("$.[*].tecnologia").value(hasItem(DEFAULT_TECNOLOGIA)));
    }
    
    @Test
    @Transactional
    public void getJuego() throws Exception {
        // Initialize the database
        juegoRepository.saveAndFlush(juego);

        // Get the juego
        restJuegoMockMvc.perform(get("/api/juegos/{id}", juego.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(juego.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.fechaPublicacion").value(DEFAULT_FECHA_PUBLICACION.toString()))
            .andExpect(jsonPath("$.protagonistas").value(DEFAULT_PROTAGONISTAS))
            .andExpect(jsonPath("$.director").value(DEFAULT_DIRECTOR))
            .andExpect(jsonPath("$.productor").value(DEFAULT_PRODUCTOR))
            .andExpect(jsonPath("$.tecnologia").value(DEFAULT_TECNOLOGIA));
    }
    @Test
    @Transactional
    public void getNonExistingJuego() throws Exception {
        // Get the juego
        restJuegoMockMvc.perform(get("/api/juegos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJuego() throws Exception {
        // Initialize the database
        juegoRepository.saveAndFlush(juego);

        int databaseSizeBeforeUpdate = juegoRepository.findAll().size();

        // Update the juego
        Juego updatedJuego = juegoRepository.findById(juego.getId()).get();
        // Disconnect from session so that the updates on updatedJuego are not directly saved in db
        em.detach(updatedJuego);
        updatedJuego
            .nombre(UPDATED_NOMBRE)
            .fechaPublicacion(UPDATED_FECHA_PUBLICACION)
            .protagonistas(UPDATED_PROTAGONISTAS)
            .director(UPDATED_DIRECTOR)
            .productor(UPDATED_PRODUCTOR)
            .tecnologia(UPDATED_TECNOLOGIA);
        JuegoDTO juegoDTO = juegoMapper.toDto(updatedJuego);

        restJuegoMockMvc.perform(put("/api/juegos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juegoDTO)))
            .andExpect(status().isOk());

        // Validate the Juego in the database
        List<Juego> juegoList = juegoRepository.findAll();
        assertThat(juegoList).hasSize(databaseSizeBeforeUpdate);
        Juego testJuego = juegoList.get(juegoList.size() - 1);
        assertThat(testJuego.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testJuego.getFechaPublicacion()).isEqualTo(UPDATED_FECHA_PUBLICACION);
        assertThat(testJuego.getProtagonistas()).isEqualTo(UPDATED_PROTAGONISTAS);
        assertThat(testJuego.getDirector()).isEqualTo(UPDATED_DIRECTOR);
        assertThat(testJuego.getProductor()).isEqualTo(UPDATED_PRODUCTOR);
        assertThat(testJuego.getTecnologia()).isEqualTo(UPDATED_TECNOLOGIA);
    }

    @Test
    @Transactional
    public void updateNonExistingJuego() throws Exception {
        int databaseSizeBeforeUpdate = juegoRepository.findAll().size();

        // Create the Juego
        JuegoDTO juegoDTO = juegoMapper.toDto(juego);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJuegoMockMvc.perform(put("/api/juegos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juegoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Juego in the database
        List<Juego> juegoList = juegoRepository.findAll();
        assertThat(juegoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJuego() throws Exception {
        // Initialize the database
        juegoRepository.saveAndFlush(juego);

        int databaseSizeBeforeDelete = juegoRepository.findAll().size();

        // Delete the juego
        restJuegoMockMvc.perform(delete("/api/juegos/{id}", juego.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Juego> juegoList = juegoRepository.findAll();
        assertThat(juegoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
