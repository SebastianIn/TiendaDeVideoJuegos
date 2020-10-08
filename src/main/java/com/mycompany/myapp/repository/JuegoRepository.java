package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Juego;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Juego entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
}
