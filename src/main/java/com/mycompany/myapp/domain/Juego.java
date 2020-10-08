package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Juego.
 */
@Entity
@Table(name = "juego")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "protagonistas")
    private String protagonistas;

    @Column(name = "director")
    private String director;

    @Column(name = "productor")
    private String productor;

    @Column(name = "tecnologia")
    private String tecnologia;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Juego nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Juego fechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
        return this;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getProtagonistas() {
        return protagonistas;
    }

    public Juego protagonistas(String protagonistas) {
        this.protagonistas = protagonistas;
        return this;
    }

    public void setProtagonistas(String protagonistas) {
        this.protagonistas = protagonistas;
    }

    public String getDirector() {
        return director;
    }

    public Juego director(String director) {
        this.director = director;
        return this;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductor() {
        return productor;
    }

    public Juego productor(String productor) {
        this.productor = productor;
        return this;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public Juego tecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
        return this;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Juego)) {
            return false;
        }
        return id != null && id.equals(((Juego) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Juego{" +
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
