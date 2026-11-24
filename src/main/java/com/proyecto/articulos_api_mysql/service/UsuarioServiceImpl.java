package com.proyecto.articulos_api_mysql.service;

import com.proyecto.articulos_api_mysql.model.Usuario;
import com.proyecto.articulos_api_mysql.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service 
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAllByOrderByEmailAsc();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        Usuario creado= usuarioRepository.save(usuario);
        creado.setPassHash("Esto no se puede mostrar!");
        return creado;
    }



}
