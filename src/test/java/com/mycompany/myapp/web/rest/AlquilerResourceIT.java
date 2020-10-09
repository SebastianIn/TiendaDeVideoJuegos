package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TIendaDeVideoJuegosApp;
import com.mycompany.myapp.domain.Alquiler;
import com.mycompany.myapp.repository.AlquilerRepository;
import com.mycompany.myapp.service.AlquilerService;
import com.mycompany.myapp.service.dto.AlquilerDTO;
import com.mycompany.myapp.service.mapper.AlquilerMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AlquilerResource} REST controller.
 */
@SpringBootTest(classes = TIendaDeVideoJuegosApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class AlquilerResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_MONTO = 1;
    private static final Integer UPDATED_MONTO = 2;

    private static final Integer DEFAULT_RANGO_EDAD_ALCOMPRAR = 1;
    private static final Integer UPDATED_RANGO_EDAD_ALCOMPRAR = 2;

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Mock
    private AlquilerRepository alquilerRepositoryMock;

    @Autowired
    private AlquilerMapper alquilerMapper;

    @Mock
    private AlquilerService alquilerServiceMock;

    @Autowired
    private AlquilerService alquilerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAlquilerMockMvc;

    private Alquiler alquiler;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alquiler createEntity(EntityManager em) {
        Alquiler alquiler = new Alquiler()
            .fecha(DEFAULT_FECHA)
            .monto(DEFAULT_MONTO)
            .rangoEdadAlcomprar(DEFAULT_RANGO_EDAD_ALCOMPRAR);
        return alquiler;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alquiler createUpdatedEntity(EntityManager em) {
        Alquiler alquiler = new Alquiler()
            .fecha(UPDATED_FECHA)
            .monto(UPDATED_MONTO)
            .rangoEdadAlcomprar(UPDATED_RANGO_EDAD_ALCOMPRAR);
        return alquiler;
    }

    @BeforeEach
    public void initTest() {
        alquiler = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlquiler() throws Exception {
        int databaseSizeBeforeCreate = alquilerRepository.findAll().size();
        // Create the Alquiler
        AlquilerDTO alquilerDTO = alquilerMapper.toDto(alquiler);
        restAlquilerMockMvc.perform(post("/api/alquilers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(alquilerDTO)))
            .andExpect(status().isCreated());

        // Validate the Alquiler in the database
        List<Alquiler> alquilerList = alquilerRepository.findAll();
        assertThat(alquilerList).hasSize(databaseSizeBeforeCreate + 1);
        Alquiler testAlquiler = alquilerList.get(alquilerList.size() - 1);
        assertThat(testAlquiler.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testAlquiler.getMonto()).isEqualTo(DEFAULT_MONTO);
        assertThat(testAlquiler.getRangoEdadAlcomprar()).isEqualTo(DEFAULT_RANGO_EDAD_ALCOMPRAR);
    }

    @Test
    @Transactional
    public void createAlquilerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alquilerRepository.findAll().size();

        // Create the Alquiler with an existing ID
        alquiler.setId(1L);
        AlquilerDTO alquilerDTO = alquilerMapper.toDto(alquiler);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlquilerMockMvc.perform(post("/api/alquilers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(alquilerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alquiler in the database
        List<Alquiler> alquilerList = alquilerRepository.findAll();
        assertThat(alquilerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAlquilers() throws Exception {
        // Initialize the database
        alquilerRepository.saveAndFlush(alquiler);

        // Get all the alquilerList
        restAlquilerMockMvc.perform(get("/api/alquilers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alquiler.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].monto").value(hasItem(DEFAULT_MONTO)))
            .andExpect(jsonPath("$.[*].rangoEdadAlcomprar").value(hasItem(DEFAULT_RANGO_EDAD_ALCOMPRAR)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllAlquilersWithEagerRelationshipsIsEnabled() throws Exception {
        when(alquilerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAlquilerMockMvc.perform(get("/api/alquilers?eagerload=true"))
            .andExpect(status().isOk());

        verify(alquilerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllAlquilersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(alquilerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAlquilerMockMvc.perform(get("/api/alquilers?eagerload=true"))
            .andExpect(status().isOk());

        verify(alquilerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getAlquiler() throws Exception {
        // Initialize the database
        alquilerRepository.saveAndFlush(alquiler);

        // Get the alquiler
        restAlquilerMockMvc.perform(get("/api/alquilers/{id}", alquiler.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(alquiler.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.monto").value(DEFAULT_MONTO))
            .andExpect(jsonPath("$.rangoEdadAlcomprar").value(DEFAULT_RANGO_EDAD_ALCOMPRAR));
    }
    @Test
    @Transactional
    public void getNonExistingAlquiler() throws Exception {
        // Get the alquiler
        restAlquilerMockMvc.perform(get("/api/alquilers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlquiler() throws Exception {
        // Initialize the database
        alquilerRepository.saveAndFlush(alquiler);

        int databaseSizeBeforeUpdate = alquilerRepository.findAll().size();

        // Update the alquiler
        Alquiler updatedAlquiler = alquilerRepository.findById(alquiler.getId()).get();
        // Disconnect from session so that the updates on updatedAlquiler are not directly saved in db
        em.detach(updatedAlquiler);
        updatedAlquiler
            .fecha(UPDATED_FECHA)
            .monto(UPDATED_MONTO)
            .rangoEdadAlcomprar(UPDATED_RANGO_EDAD_ALCOMPRAR);
        AlquilerDTO alquilerDTO = alquilerMapper.toDto(updatedAlquiler);

        restAlquilerMockMvc.perform(put("/api/alquilers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(alquilerDTO)))
            .andExpect(status().isOk());

        // Validate the Alquiler in the database
        List<Alquiler> alquilerList = alquilerRepository.findAll();
        assertThat(alquilerList).hasSize(databaseSizeBeforeUpdate);
        Alquiler testAlquiler = alquilerList.get(alquilerList.size() - 1);
        assertThat(testAlquiler.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testAlquiler.getMonto()).isEqualTo(UPDATED_MONTO);
        assertThat(testAlquiler.getRangoEdadAlcomprar()).isEqualTo(UPDATED_RANGO_EDAD_ALCOMPRAR);
    }

    @Test
    @Transactional
    public void updateNonExistingAlquiler() throws Exception {
        int databaseSizeBeforeUpdate = alquilerRepository.findAll().size();

        // Create the Alquiler
        AlquilerDTO alquilerDTO = alquilerMapper.toDto(alquiler);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlquilerMockMvc.perform(put("/api/alquilers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(alquilerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alquiler in the database
        List<Alquiler> alquilerList = alquilerRepository.findAll();
        assertThat(alquilerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAlquiler() throws Exception {
        // Initialize the database
        alquilerRepository.saveAndFlush(alquiler);

        int databaseSizeBeforeDelete = alquilerRepository.findAll().size();

        // Delete the alquiler
        restAlquilerMockMvc.perform(delete("/api/alquilers/{id}", alquiler.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Alquiler> alquilerList = alquilerRepository.findAll();
        assertThat(alquilerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
