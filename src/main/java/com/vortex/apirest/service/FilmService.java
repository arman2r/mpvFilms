package com.vortex.apirest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vortex.apirest.DTO.FilmRequest;
import com.vortex.apirest.Entity.FilmEntity;
import com.vortex.apirest.Mapper.FilmMapper;
import com.vortex.apirest.Repository.FilmRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final ImageService imageService;
    private final FilmMapper filmMapper;

    public FilmService(FilmRepository filmRepository, ImageService imageService, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.imageService = imageService;
        this.filmMapper = filmMapper;
    }

    public FilmEntity createFilm(FilmRequest filmRequest) throws Exception {
        FilmEntity film = filmMapper.toEntity(filmRequest);
        FilmEntity savedFilm = filmRepository.save(film);

        if (filmRequest.getBase64Image() != null && !filmRequest.getBase64Image().isEmpty()) {
            String imageUrl = imageService.uploadBase64Image(filmRequest.getBase64Image());
            savedFilm.setCover(imageUrl);
            return filmRepository.save(savedFilm);
        }

        return savedFilm;
    }

    public FilmEntity getFilmById(Integer id) {
        return filmRepository.findById(id).orElse(null);
    }

    public FilmEntity updateFilm(Integer id, FilmRequest filmRequest) throws Exception {
        FilmEntity filmToUpdate = filmRepository.findById(id).orElse(null);
        if (filmToUpdate == null)
            throw new RuntimeException("Film not found");
    
        // Mapear los datos del request al entity existente
        FilmEntity updatedFilm = filmMapper.toEntity(filmRequest);
        updatedFilm.setId(id);
        updatedFilm.setCover(filmToUpdate.getCover()); // Mantener la imagen actual si no se actualiza
    
        // Si hay una imagen en Base64, se elimina la anterior y se sube la nueva
        if (filmRequest.getBase64Image() != null && !filmRequest.getBase64Image().isEmpty()) {
            if (filmToUpdate.getCover() != null)
                imageService.deleteImage(filmToUpdate.getCover());
            updatedFilm.setCover(imageService.uploadBase64Image(filmRequest.getBase64Image()));
        }
    
        return filmRepository.save(updatedFilm);
    }
    

    public void deleteFilm(Integer id) {
        FilmEntity film = filmRepository.findById(id).orElse(null);
        if (film == null) throw new RuntimeException("Film not found");

        if (film.getCover() != null) imageService.deleteImage(film.getCover());

        filmRepository.delete(film);
    }

    public Iterable<FilmEntity> getAllFilms() {
        return filmRepository.findAll();
    }

    public List<FilmEntity> searchFilmsByTitle(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<FilmEntity> getAvailableFilms() {
        return filmRepository.findByAvailableTrue();
    }

    public List<FilmEntity> getFilmsByPriceGreaterThan(Double price) {
        return filmRepository.findFilmsByPriceGreaterThan(price);
    }

}
