package com.github.erodriguezg.springbootangular.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.github.erodriguezg.springbootangular.services.entities.PerfilUsuario;

@Repository
public class PerfilUsuarioDao {

	@PersistenceContext
	private EntityManager em;

	public List<PerfilUsuario> traerTodos() {
		return em.createNamedQuery("PerfilUsuario.findAll", PerfilUsuario.class).getResultList();
	}

}
