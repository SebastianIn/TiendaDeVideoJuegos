package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TarifasTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tarifas.class);
        Tarifas tarifas1 = new Tarifas();
        tarifas1.setId(1L);
        Tarifas tarifas2 = new Tarifas();
        tarifas2.setId(tarifas1.getId());
        assertThat(tarifas1).isEqualTo(tarifas2);
        tarifas2.setId(2L);
        assertThat(tarifas1).isNotEqualTo(tarifas2);
        tarifas1.setId(null);
        assertThat(tarifas1).isNotEqualTo(tarifas2);
    }
}
