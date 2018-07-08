package com.github.erodriguezg.springbootangular.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.github.erodriguezg.springbootangular.services.entities.Comuna;
import com.github.erodriguezg.springbootangular.services.entities.Provincia;

@Repository
public class ComunaDao {

	@PersistenceContext
	private EntityManager em;

	public Comuna traerPorId(Integer idComuna) {
		return em.find(Comuna.class, idComuna);
	}

	public List<Comuna> traerPorProvincia(Provincia provincia) {
		return em.createNamedQuery("Comuna.traerPorProvincia", Comuna.class).setParameter("provincia", provincia)
				.getResultList();
	}

}
