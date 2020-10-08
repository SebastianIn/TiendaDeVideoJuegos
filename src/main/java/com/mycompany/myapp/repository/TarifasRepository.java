package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Tarifas;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tarifas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TarifasRepository extends JpaRepository<Tarifas, Long> {
}
