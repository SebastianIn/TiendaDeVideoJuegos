package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Inventario.
 */
@Entity
@Table(name = "inventario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "total")
    private Integer total;

    @Column(name = "disponibles")
    private Integer disponibles;

    @Column(name = "en_alquiler")
    private Integer enAlquiler;

    @OneToOne
    @JoinColumn(unique = true)
    private Juego juego;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public Inventario total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDisponibles() {
        return disponibles;
    }

    public Inventario disponibles(Integer disponibles) {
        this.disponibles = disponibles;
        return this;
    }

    public void setDisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    public Integer getEnAlquiler() {
        return enAlquiler;
    }

    public Inventario enAlquiler(Integer enAlquiler) {
        this.enAlquiler = enAlquiler;
        return this;
    }

    public void setEnAlquiler(Integer enAlquiler) {
        this.enAlquiler = enAlquiler;
    }

    public Juego getJuego() {
        return juego;
    }

    public Inventario juego(Juego juego) {
        this.juego = juego;
        return this;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventario)) {
            return false;
        }
        return id != null && id.equals(((Inventario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inventario{" +
            "id=" + getId() +
            ", total=" + getTotal() +
            ", disponibles=" + getDisponibles() +
            ", enAlquiler=" + getEnAlquiler() +
            "}";
    }
}
