package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Alquiler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Alquiler entity.
 */
@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    @Query(value = "select distinct alquiler from Alquiler alquiler left join fetch alquiler.juegos",
        countQuery = "select count(distinct alquiler) from Alquiler alquiler")
    Page<Alquiler> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct alquiler from Alquiler alquiler left join fetch alquiler.juegos")
    List<Alquiler> findAllWithEagerRelationships();

    @Query("select alquiler from Alquiler alquiler left join fetch alquiler.juegos where alquiler.id =:id")
    Optional<Alquiler> findOneWithEagerRelationships(@Param("id") Long id);
}
