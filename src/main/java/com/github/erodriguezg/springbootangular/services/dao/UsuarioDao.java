package com.github.erodriguezg.springbootangular.services.dao;

import com.github.erodriguezg.springbootangular.services.dto.UsuarioFiltroDto;
import com.github.erodriguezg.springbootangular.services.entities.Usuario;
import com.github.erodriguezg.springbootangular.utils.JpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JpaUtils jpaUtils;

	public Usuario traerPorId(Long id) {
		return em.find(Usuario.class, id);
	}

	public Usuario traerPorUsername(String username) {
		return jpaUtils.resultForOneObject(
				em.createNamedQuery("Usuario.findByUsername", Usuario.class).setParameter("username", username));
	}

	public List<Usuario> taerTodos() {
		return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
	}


	public long traerPorFiltroRowCount(UsuarioFiltroDto filtroDto) {
		String proyection = "select count(distinct u) ";
		TypedQuery<Long> query = crearQueryTraerPorFiltros(filtroDto, proyection, Long.class);
		return jpaUtils.resultForOneObject(query);
	}

	public List<Usuario> traerPorFiltro(UsuarioFiltroDto filtroDto, int inicio, int fin) {
		String proyection = "select distinct u ";
		TypedQuery<Usuario> query = crearQueryTraerPorFiltros(filtroDto, proyection, Usuario.class);
		return query
				.setFirstResult(inicio)
				.setMaxResults(inicio + fin)
				.getResultList();
	}

	public Usuario traerPorRun(Integer run) {
		return jpaUtils
				.resultForOneObject(em.createNamedQuery("Usuario.findByRun", Usuario.class).setParameter("run", run));
	}

	public Usuario traerPorEmail(String email) {
		return jpaUtils.resultForOneObject(
				em.createNamedQuery("Usuario.findByEmail", Usuario.class).setParameter("email", email));
	}

	/*
	 * PRIVADOS
	 */

	private <T> TypedQuery<T> crearQueryTraerPorFiltros(UsuarioFiltroDto filtroDto, String proyection, Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Map<String, Object> parametros = this.jpaUtils.createEmptyParams();
		query.append(proyection);
		query.append("from Usuario u ");
		query.append("inner join u.persona p ");
		query.append("inner join u.idPerfilUsuario perfil ");
		query.append("where 1 = 1 ");
		queryBuscarCondicionUsername(query, parametros, filtroDto);
		queryBuscarCondicionNombres(query, parametros, filtroDto);
		queryBuscarCondicionApellidoPaterno(query, parametros, filtroDto);
		queryBuscarCondicionApellidoMaterno(query, parametros, filtroDto);
		queryBuscarCondicionRut(query, parametros, filtroDto);
		queryBuscarCondicionPerfil(query, parametros, filtroDto);
		queryBuscarCondicionEmail(query, parametros, filtroDto);
		queryBuscarCondicionFechaNacimientoInferior(query, parametros, filtroDto);
		queryBuscarCondicionFechaNacimientoSuperior(query, parametros, filtroDto);
		queryBuscarCondicionHabilitado(query, parametros, filtroDto);
		TypedQuery<T> typedQuery = em.createQuery(query.toString(), clazz);
		jpaUtils.cargarMapParametrosEnQuery(typedQuery, parametros);
		return typedQuery;
	}

	private void queryBuscarCondicionUsername(StringBuilder query, Map<String, Object> parametros, UsuarioFiltroDto filtroDto) {
		if (filtroDto.getUsername() != null && !filtroDto.getUsername().isEmpty()) {
			query.append("and upper(u.username) like upper(:username) ");
			parametros.put("username", "%" + filtroDto.getUsername() + "%");
		}
	}

	private void queryBuscarCondicionNombres(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getNombres() != null && !usuarioFilterDto.getNombres().isEmpty()) {
			query.append("and upper(p.nombres) like upper(:nombres) ");
			parametros.put("nombres", "%" + usuarioFilterDto.getNombres() + "%");
		}
	}

	private void queryBuscarCondicionApellidoPaterno(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getApPaterno() != null && !usuarioFilterDto.getApPaterno().isEmpty()) {
			query.append("and upper(p.apellidoPaterno) like upper(:apPaterno) ");
			parametros.put("apPaterno", "%" + usuarioFilterDto.getApPaterno() + "%");
		}
	}

	private void queryBuscarCondicionApellidoMaterno(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getApMaterno() != null && !usuarioFilterDto.getApMaterno().isEmpty()) {
			query.append("and upper(p.apellidoMaterno) like upper(:apMaterno) ");
			parametros.put("apMaterno", "%" + usuarioFilterDto.getApMaterno() + "%");
		}
	}

	private void queryBuscarCondicionRut(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getRun() != null) {
			query.append("and p.run = :run ");
			parametros.put("run", usuarioFilterDto.getRun());
		}
	}

	private void queryBuscarCondicionPerfil(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getPerfilDto() != null && usuarioFilterDto.getPerfilDto().getId() != null) {
			query.append("and perfil.idPerfilUsuario = :idPerfil ");
			parametros.put("idPerfil", usuarioFilterDto.getPerfilDto().getId());
		}
	}

	private void queryBuscarCondicionEmail(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getEmail() != null && !usuarioFilterDto.getEmail().isEmpty()) {
			query.append("and upper(p.email) like upper(:email) ");
			parametros.put("email", "%" + usuarioFilterDto.getEmail() + "%");
		}
	}

	private void queryBuscarCondicionFechaNacimientoInferior(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getFechaNacimientoInferior() != null) {
			query.append("and p.fechanacimiento >= :fechaNacimientoInf ");
			parametros.put("fechaNacimientoInf", usuarioFilterDto.getFechaNacimientoInferior());
		}
	}

	private void queryBuscarCondicionFechaNacimientoSuperior(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getFechaNacimientoSuperior() != null) {
			query.append("and p.fechanacimiento <= :fechaNacimientoSup ");
			parametros.put("fechaNacimientoSup", usuarioFilterDto.getFechaNacimientoSuperior());
		}
	}

	private void queryBuscarCondicionHabilitado(StringBuilder query, Map<String, Object> parametros,
			UsuarioFiltroDto usuarioFilterDto) {
		if (usuarioFilterDto.getHabilitado() != null) {
			query.append("and u.habilitado = :habilitado ");
			parametros.put("habilitado", usuarioFilterDto.getHabilitado());
		}
	}

}
