package com.proyecto.articulos_api_mysql.service;

import com.proyecto.articulos_api_mysql.model.Articulo;
import com.proyecto.articulos_api_mysql.repository.ArticuloRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {
    private final ArticuloRepository articuloRepository;

    private String normalizarNombre(String nombre) {
        if (nombre == null) {
            return null;
        }
        return nombre.trim().toUpperCase();
    }

    @Autowired
    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }
     
    @Override
    public List<Articulo> listarArticulos() {
        return articuloRepository.findAllByOrderByNombreAsc();
    }

    @Override
    public Optional<Articulo> obtenerArticuloPorId(Long id) {
        return articuloRepository.findById(id);
    }

    @Override
    public Articulo guardarArticulo(Articulo articulo) {
        articulo.setNombre(normalizarNombre(articulo.getNombre()));
        return articuloRepository.save(articulo);
    }
    
    @Override
    public Articulo actualizarArticulo(Long id, Articulo articulo) {
        Articulo existente = articuloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un artículo con id " + id));
        
        if (articulo.getNombre() != null && !articulo.getNombre().isBlank()){
            existente.setNombre(normalizarNombre(articulo.getNombre()));
        }
                
        if (articulo.getPrecio() != null){
            existente.setPrecio(articulo.getPrecio());
        }
        
        return articuloRepository.save(existente);
    }

    @Override
    public void eliminarArticulo(Long id) {
        if (!articuloRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe un artículo con id " + id);
        }

        articuloRepository.deleteById(id);
    }

    @Override
    public List<Articulo> filtrarArticulosPorNombre(String texto) {
        if (texto == null || texto.isBlank()) {
            return listarArticulos();
        }
        String normalizado = normalizarNombre(texto);
        return articuloRepository.buscarSinTildes(normalizado);
    }
}
