package com.github.erodriguezg.springbootangular.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.github.erodriguezg.springbootangular.services.entities.Region;

@Repository
public class RegionDao {

	@PersistenceContext
	private EntityManager em;

	public List<Region> traerTodas() {
		return em.createNamedQuery("Region.traerTodas", Region.class).getResultList();
	}

}
