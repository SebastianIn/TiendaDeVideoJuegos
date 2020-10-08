package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JuegoMapperTest {

    private JuegoMapper juegoMapper;

    @BeforeEach
    public void setUp() {
        juegoMapper = new JuegoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(juegoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(juegoMapper.fromId(null)).isNull();
    }
}
