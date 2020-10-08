package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class JuegoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JuegoDTO.class);
        JuegoDTO juegoDTO1 = new JuegoDTO();
        juegoDTO1.setId(1L);
        JuegoDTO juegoDTO2 = new JuegoDTO();
        assertThat(juegoDTO1).isNotEqualTo(juegoDTO2);
        juegoDTO2.setId(juegoDTO1.getId());
        assertThat(juegoDTO1).isEqualTo(juegoDTO2);
        juegoDTO2.setId(2L);
        assertThat(juegoDTO1).isNotEqualTo(juegoDTO2);
        juegoDTO1.setId(null);
        assertThat(juegoDTO1).isNotEqualTo(juegoDTO2);
    }
}
