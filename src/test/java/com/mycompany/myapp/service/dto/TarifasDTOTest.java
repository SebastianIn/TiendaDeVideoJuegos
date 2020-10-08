package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TarifasDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TarifasDTO.class);
        TarifasDTO tarifasDTO1 = new TarifasDTO();
        tarifasDTO1.setId(1L);
        TarifasDTO tarifasDTO2 = new TarifasDTO();
        assertThat(tarifasDTO1).isNotEqualTo(tarifasDTO2);
        tarifasDTO2.setId(tarifasDTO1.getId());
        assertThat(tarifasDTO1).isEqualTo(tarifasDTO2);
        tarifasDTO2.setId(2L);
        assertThat(tarifasDTO1).isNotEqualTo(tarifasDTO2);
        tarifasDTO1.setId(null);
        assertThat(tarifasDTO1).isNotEqualTo(tarifasDTO2);
    }
}
