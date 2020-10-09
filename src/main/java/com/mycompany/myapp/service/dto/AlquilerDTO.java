package com.mycompany.myapp.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Alquiler} entity.
 */
public class AlquilerDTO implements Serializable {
    
    private Long id;

    private LocalDate fecha;

    private Integer monto;

    private Integer rangoEdadAlcomprar;


    private Long clienteId;

    private String clienteCedula;
    private Set<JuegoDTO> juegos = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getRangoEdadAlcomprar() {
        return rangoEdadAlcomprar;
    }

    public void setRangoEdadAlcomprar(Integer rangoEdadAlcomprar) {
        this.rangoEdadAlcomprar = rangoEdadAlcomprar;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(String clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public Set<JuegoDTO> getJuegos() {
        return juegos;
    }

    public void setJuegos(Set<JuegoDTO> juegos) {
        this.juegos = juegos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlquilerDTO)) {
            return false;
        }

        return id != null && id.equals(((AlquilerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AlquilerDTO{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", monto=" + getMonto() +
            ", rangoEdadAlcomprar=" + getRangoEdadAlcomprar() +
            ", clienteId=" + getClienteId() +
            ", clienteCedula='" + getClienteCedula() + "'" +
            ", juegos='" + getJuegos() + "'" +
            "}";
    }
}
