package com.mycompany.myapp.repository;

import java.util.Optional;

import com.mycompany.myapp.domain.Cliente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cliente entity.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findOneByCedula(String cedula); 
}
