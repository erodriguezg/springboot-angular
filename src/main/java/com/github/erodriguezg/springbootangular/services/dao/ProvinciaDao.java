package com.github.erodriguezg.springbootangular.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.github.erodriguezg.springbootangular.services.entities.Provincia;
import com.github.erodriguezg.springbootangular.services.entities.Region;

@Repository
public class ProvinciaDao {

	@PersistenceContext
	private EntityManager em;

	public List<Provincia> traerPorRegion(Region region) {
		return em.createNamedQuery("Provincia.traerPorRegion", Provincia.class)
				.setParameter("region", region)
				.getResultList();
	}

}
