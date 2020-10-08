package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Tarifas.
 */
@Entity
@Table(name = "tarifas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tarifas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "precio", precision = 21, scale = 2)
    private BigDecimal precio;

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public Tarifas precio(BigDecimal precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Juego getJuego() {
        return juego;
    }

    public Tarifas juego(Juego juego) {
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
        if (!(o instanceof Tarifas)) {
            return false;
        }
        return id != null && id.equals(((Tarifas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tarifas{" +
            "id=" + getId() +
            ", precio=" + getPrecio() +
            "}";
    }
}
