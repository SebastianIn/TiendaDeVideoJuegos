package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Alquiler.
 */
@Entity
@Table(name = "alquiler")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Alquiler implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "monto")
    private Integer monto;

    @Column(name = "rango_edad_alcomprar")
    private Integer rangoEdadAlcomprar;

    @ManyToOne
    @JsonIgnoreProperties(value = "alquilers", allowSetters = true)
    private Cliente cliente;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "alquiler_juegos",
               joinColumns = @JoinColumn(name = "alquiler_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "juegos_id", referencedColumnName = "id"))
    private Set<Juego> juegos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Alquiler fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getMonto() {
        return monto;
    }

    public Alquiler monto(Integer monto) {
        this.monto = monto;
        return this;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getRangoEdadAlcomprar() {
        return rangoEdadAlcomprar;
    }

    public Alquiler rangoEdadAlcomprar(Integer rangoEdadAlcomprar) {
        this.rangoEdadAlcomprar = rangoEdadAlcomprar;
        return this;
    }

    public void setRangoEdadAlcomprar(Integer rangoEdadAlcomprar) {
        this.rangoEdadAlcomprar = rangoEdadAlcomprar;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Alquiler cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Juego> getJuegos() {
        return juegos;
    }

    public Alquiler juegos(Set<Juego> juegos) {
        this.juegos = juegos;
        return this;
    }

    public Alquiler addJuegos(Juego juego) {
        this.juegos.add(juego);
        return this;
    }

    public Alquiler removeJuegos(Juego juego) {
        this.juegos.remove(juego);
        return this;
    }

    public void setJuegos(Set<Juego> juegos) {
        this.juegos = juegos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Alquiler)) {
            return false;
        }
        return id != null && id.equals(((Alquiler) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Alquiler{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", monto=" + getMonto() +
            ", rangoEdadAlcomprar=" + getRangoEdadAlcomprar() +
            "}";
    }
}
