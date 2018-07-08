package com.github.erodriguezg.springbootangular.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

@Component
public class JpaUtils {

	@PersistenceContext
	private EntityManager em;

	public <T> T resultForOneObject(TypedQuery<T> query) {
		List<T> lista = query.getResultList();
		if (lista.isEmpty()) {
			return null;
		}
		if (lista.size() > 1) {
			throw new NonUniqueResultException();
		}
		return lista.get(0);
	}

	public <T> T resultForFirstObject(TypedQuery<T> query) {
		List<T> lista = query.getResultList();
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	public Map<String, Object> createEmptyParams() {
		return new HashMap<>();
	}

	public <T> TypedQuery<T> cargarMapParametrosEnQuery(TypedQuery<T> query, Map<String, Object> params) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}

	public Query cargarMapParametrosEnQuery(Query query, Map<String, Object> params) {
		if (params == null) {
			throw new IllegalStateException("params son nulos!");
		}
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}

	public <T> List<T> queryForList(StringBuilder queryBuilder, Map<String, Object> params, Class<T> queryClass) {
		return queryForList(queryBuilder.toString(), params, queryClass);
	}

	public <T> List<T> queryForList(String query, Map<String, Object> params, Class<T> queryClass) {
		TypedQuery<T> typedQuery = em.createQuery(query, queryClass);
		typedQuery = cargarMapParametrosEnQuery(typedQuery, params);
		return typedQuery.getResultList();
	}

}