package com.vortex.apirest.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vortex.apirest.service.ImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/upload/file")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestPart("image") MultipartFile image) {
        try {
            return ResponseEntity.ok(imageService.uploadImage(image));
        } catch (Exception e) {
            log.error("Error al subir la imagen", e);
            return ResponseEntity.internalServerError().body("Error al subir la imagen");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteImage(@RequestParam("url") String imageUrl) {
        try {
            imageService.deleteImage(imageUrl);
            return ResponseEntity.ok("Imagen eliminada correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar la imagen", e);
            return ResponseEntity.internalServerError().body("Error al eliminar la imagen");
        }
    }
}
