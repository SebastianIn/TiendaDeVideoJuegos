package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Tarifas} entity.
 */
public class TarifasDTO implements Serializable {
    
    private Long id;

    private BigDecimal precio;


    private Long juegoId;

    private String juegoNombre;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }

    public String getJuegoNombre() {
        return juegoNombre;
    }

    public void setJuegoNombre(String juegoNombre) {
        this.juegoNombre = juegoNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TarifasDTO)) {
            return false;
        }

        return id != null && id.equals(((TarifasDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TarifasDTO{" +
            "id=" + getId() +
            ", precio=" + getPrecio() +
            ", juegoId=" + getJuegoId() +
            ", juegoNombre='" + getJuegoNombre() + "'" +
            "}";
    }
}
