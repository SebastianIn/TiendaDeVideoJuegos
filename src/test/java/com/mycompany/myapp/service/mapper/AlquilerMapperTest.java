package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AlquilerMapperTest {

    private AlquilerMapper alquilerMapper;

    @BeforeEach
    public void setUp() {
        alquilerMapper = new AlquilerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(alquilerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(alquilerMapper.fromId(null)).isNull();
    }
}
