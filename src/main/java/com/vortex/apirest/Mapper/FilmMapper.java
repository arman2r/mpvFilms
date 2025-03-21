package com.vortex.apirest.Mapper;

import com.vortex.apirest.DTO.FilmRequest;
import com.vortex.apirest.Entity.FilmEntity;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {
    public FilmEntity toEntity(FilmRequest filmRequest) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setTitle(filmRequest.getTitle());
        filmEntity.setDescription(filmRequest.getDescription());
        filmEntity.setReleaseDate(filmRequest.getReleaseDate());
        filmEntity.setPrice(filmRequest.getPrice());
        filmEntity.setStock(filmRequest.getStock());
        filmEntity.setAvailable(filmRequest.getAvailable());
        return filmEntity;
    }
}