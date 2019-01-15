package com.github.erodriguezg.springbootangular.repository;

import com.github.erodriguezg.springbootangular.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {


}
