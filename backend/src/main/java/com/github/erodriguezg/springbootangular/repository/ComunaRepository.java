package com.github.erodriguezg.springbootangular.repository;

import com.github.erodriguezg.springbootangular.entities.Comuna;
import com.github.erodriguezg.springbootangular.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

    List<Comuna> findByProvincia(Provincia provincia);

}
