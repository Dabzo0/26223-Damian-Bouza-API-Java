package com.proyecto.articulos_api_mysql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity 
@Table (name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id_usuario;

    @NotBlank
    private String email;

    private String pass_hash;

    public Usuario(){}

    public Usuario(Long id_usuario, String email, String pass_hash){
        this.id_usuario = id_usuario;
        this.email= email;
        this.pass_hash = pass_hash;
    }

    public Long getIdUsuario() {return id_usuario;}
    public void setIdUsuario(Long id){this.id_usuario=id;}
    public String getEmail() {return email;}
    public void setEmail(String email){this.email=email;}
    public String getPassHash(){return pass_hash;}
    public void setPassHash(String hash){this.pass_hash=hash;}
}
