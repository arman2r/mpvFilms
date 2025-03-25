package com.vortex.apirest.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vortex.apirest.Entity.FilmEntity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

    // Buscar película por ID (ya incluido en JpaRepository)
    Optional<FilmEntity> findById(Integer id);

    // Buscar películas por título
    List<FilmEntity> findByTitleContainingIgnoreCase(String title);

    // Buscar películas disponibles
    List<FilmEntity> findByAvailableTrue();

    // Consulta personalizada con JPQL
    @Query("SELECT f FROM FilmEntity f WHERE f.price < :price")
    List<FilmEntity> findFilmsByPriceGreaterThan(@Param("price") Double price);

}
