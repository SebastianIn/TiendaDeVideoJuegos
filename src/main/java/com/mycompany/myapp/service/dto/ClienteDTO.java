package com.mycompany.myapp.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Cliente} entity.
 */
public class ClienteDTO implements Serializable {
    
    private Long id;

    private String nombre;

    private String apellido;

    private String cedula;

    private LocalDate fechaNacimiento;

    private Integer rangoEdad;

    
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getRangoEdad() {
        return rangoEdad;
    }

    public void setRangoEdad(Integer rangoEdad) {
        this.rangoEdad = rangoEdad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClienteDTO)) {
            return false;
        }

        return id != null && id.equals(((ClienteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClienteDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", cedula='" + getCedula() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", rangoEdad=" + getRangoEdad() +
            "}";
    }
}
