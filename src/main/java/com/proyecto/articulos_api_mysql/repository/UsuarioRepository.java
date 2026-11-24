package com.proyecto.articulos_api_mysql.repository;

import com.proyecto.articulos_api_mysql.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    List<Usuario> findAllByOrderByEmailAsc();

}
