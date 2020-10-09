package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AlquilerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Alquiler.class);
        Alquiler alquiler1 = new Alquiler();
        alquiler1.setId(1L);
        Alquiler alquiler2 = new Alquiler();
        alquiler2.setId(alquiler1.getId());
        assertThat(alquiler1).isEqualTo(alquiler2);
        alquiler2.setId(2L);
        assertThat(alquiler1).isNotEqualTo(alquiler2);
        alquiler1.setId(null);
        assertThat(alquiler1).isNotEqualTo(alquiler2);
    }
}
