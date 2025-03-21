package com.vortex.apirest.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vortex.apirest.DTO.FilmRequest;
import com.vortex.apirest.Entity.FilmEntity;
import com.vortex.apirest.service.FilmService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/film")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<FilmEntity> createFilm(@RequestBody FilmRequest filmRequest) {
        try {
            return ResponseEntity.ok(filmService.createFilm(filmRequest));
        } catch (Exception e) {
            log.error("Error al crear la película", e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmEntity> getFilmById(@PathVariable Integer id) {
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmEntity> updateFilm(
        @PathVariable Integer id,
        @RequestBody FilmRequest filmRequest) {
    try {
        log.info("Updating film with ID: {}", id);
        return ResponseEntity.ok(filmService.updateFilm(id, filmRequest));
    } catch (Exception e) {
        log.error("Error al actualizar la película", e);
        return ResponseEntity.internalServerError().body(null);
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Integer id) {
        try {
            filmService.deleteFilm(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar la película", e);
            return ResponseEntity.status(500).body("Error al eliminar la película: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<FilmEntity>> getAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @GetMapping("/search")
    public ResponseEntity<List<FilmEntity>> searchFilms(@RequestParam String title) {
        return ResponseEntity.ok(filmService.searchFilmsByTitle(title));
    }

    @GetMapping("/available")
    public ResponseEntity<List<FilmEntity>> getAvailableFilms() {
        return ResponseEntity.ok(filmService.getAvailableFilms());
    }

    @GetMapping("/price")
    public ResponseEntity<List<FilmEntity>> getFilmsByPrice(@RequestParam Double price) {
        return ResponseEntity.ok(filmService.getFilmsByPriceGreaterThan(price));
    }
}
