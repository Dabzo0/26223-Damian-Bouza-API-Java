package com.proyecto.articulos_api_mysql.repository;

import com.proyecto.articulos_api_mysql.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    
    List<Articulo> findByNombre(String nombre);

    // Buscar artículos cuyo nombre contenga una palabra (LIKE '%texto%')
    List<Articulo> findByNombreContaining(String texto);

    // Buscar artículos con precio mayor a un valor dado
    List<Articulo> findByPrecioGreaterThan(Double precio);

    // Buscar artículos con precio entre dos valores
    List<Articulo> findByPrecioBetween(Double min, Double max);

    // Buscar por nombre ignorando mayúsculas y minúsculas
    List<Articulo> findByNombreIgnoreCase(String nombre);

    // Buscar artículos ordenados por precio ascendente
    List<Articulo> findAllByOrderByPrecioAsc();

    // Buscar artículos ordenados por nombre ascendente
    List<Articulo> findAllByOrderByNombreAsc();

    // Buscar artículos por nombre y precio mayor a cierto valor
    List<Articulo> findByNombreContainingAndPrecioGreaterThan(String nombre, Double precio);

    @Query("""
        SELECT a FROM Articulo a
        WHERE LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(a.nombre, 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'))
        LIKE LOWER(CONCAT('%', REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(:texto, 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), '%'))
        ORDER BY a.nombre ASC
    """)
    List<Articulo> buscarSinTildes(@Param ("texto") String texto);
    
}
