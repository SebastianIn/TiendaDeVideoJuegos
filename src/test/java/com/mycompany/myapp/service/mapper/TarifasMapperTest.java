package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TarifasMapperTest {

    private TarifasMapper tarifasMapper;

    @BeforeEach
    public void setUp() {
        tarifasMapper = new TarifasMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tarifasMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tarifasMapper.fromId(null)).isNull();
    }
}
