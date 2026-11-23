package com.proyecto.articulos_api_mysql.controller;

import com.proyecto.articulos_api_mysql.model.Articulo;
import com.proyecto.articulos_api_mysql.service.ArticuloService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (CORS)
@RestController // Expone métodos como API REST
@RequestMapping("/api/articulos") // Ruta base
public class ArticuloController {
    private final ArticuloService articuloService;
    
    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public List<Articulo> filtrar(@RequestParam(required = false) String nombre) {
        return articuloService.filtrarArticulosPorNombre(nombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
        return articuloService.obtenerArticuloPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Artículo no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Articulo> crear(@Valid @RequestBody Articulo articulo) {
        Articulo creado = articuloService.guardarArticulo(articulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
        try {
            Articulo actualizado = articuloService.actualizarArticulo(id, articulo);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Artículo no encontrado con id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
        try {
            articuloService.eliminarArticulo(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Artículo no encontrado con id " + id);
        }
    }

}
