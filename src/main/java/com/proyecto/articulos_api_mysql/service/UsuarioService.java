package com.proyecto.articulos_api_mysql.service;

import com.proyecto.articulos_api_mysql.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario guardarUsuario (Usuario usuario);
}
