package com.mycompany.myapp.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Juego} entity.
 */
public class JuegoDTO implements Serializable {
    
    private Long id;

    private String nombre;

    private LocalDate fechaPublicacion;

    private String protagonistas;

    private String director;

    private String productor;

    private String tecnologia;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getProtagonistas() {
        return protagonistas;
    }

    public void setProtagonistas(String protagonistas) {
        this.protagonistas = protagonistas;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JuegoDTO)) {
            return false;
        }

        return id != null && id.equals(((JuegoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JuegoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", fechaPublicacion='" + getFechaPublicacion() + "'" +
            ", protagonistas='" + getProtagonistas() + "'" +
            ", director='" + getDirector() + "'" +
            ", productor='" + getProductor() + "'" +
            ", tecnologia='" + getTecnologia() + "'" +
            "}";
    }
}
