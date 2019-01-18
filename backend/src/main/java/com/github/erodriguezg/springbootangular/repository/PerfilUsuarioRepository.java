package com.github.erodriguezg.springbootangular.repository;

import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {



}
