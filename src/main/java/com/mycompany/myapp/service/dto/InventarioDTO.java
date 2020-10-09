package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Inventario} entity.
 */
public class InventarioDTO implements Serializable {
    
    private Long id;

    private Integer total;

    private Integer disponibles;

    private Integer enAlquiler;


    private Long juegoId;

    private String juegoNombre;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    public Integer getEnAlquiler() {
        return enAlquiler;
    }

    public void setEnAlquiler(Integer enAlquiler) {
        this.enAlquiler = enAlquiler;
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
        if (!(o instanceof InventarioDTO)) {
            return false;
        }

        return id != null && id.equals(((InventarioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InventarioDTO{" +
            "id=" + getId() +
            ", total=" + getTotal() +
            ", disponibles=" + getDisponibles() +
            ", enAlquiler=" + getEnAlquiler() +
            ", juegoId=" + getJuegoId() +
            ", juegoNombre='" + getJuegoNombre() + "'" +
            "}";
    }
}
