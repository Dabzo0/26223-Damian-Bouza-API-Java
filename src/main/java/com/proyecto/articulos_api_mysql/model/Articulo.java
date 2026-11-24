package com.proyecto.articulos_api_mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "articulos")
public class Articulo {
    
    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;
    
    @NotNull(message = "El precio es obligatorio.")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a cero.")
    private Double precio;
    
    private String descripcion;
    private String imagen;
   

    public Articulo() {}

    public Articulo(Long id, String nombre, Double precio, String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

}
