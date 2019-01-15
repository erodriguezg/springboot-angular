package com.github.erodriguezg.springbootangular.repository;

import com.github.erodriguezg.springbootangular.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {


    Usuario findByUsername(String username);

    Usuario findByRun(Integer run);

    Usuario findByEmail(String email);



}
