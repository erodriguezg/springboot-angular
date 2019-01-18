package com.github.erodriguezg.springbootangular.repository;

import com.github.erodriguezg.springbootangular.entities.Provincia;
import com.github.erodriguezg.springbootangular.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {

    List<Provincia> findByRegion(Region region);

}
