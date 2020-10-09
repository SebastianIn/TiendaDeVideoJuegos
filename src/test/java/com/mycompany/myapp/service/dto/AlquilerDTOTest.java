package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AlquilerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlquilerDTO.class);
        AlquilerDTO alquilerDTO1 = new AlquilerDTO();
        alquilerDTO1.setId(1L);
        AlquilerDTO alquilerDTO2 = new AlquilerDTO();
        assertThat(alquilerDTO1).isNotEqualTo(alquilerDTO2);
        alquilerDTO2.setId(alquilerDTO1.getId());
        assertThat(alquilerDTO1).isEqualTo(alquilerDTO2);
        alquilerDTO2.setId(2L);
        assertThat(alquilerDTO1).isNotEqualTo(alquilerDTO2);
        alquilerDTO1.setId(null);
        assertThat(alquilerDTO1).isNotEqualTo(alquilerDTO2);
    }
}
