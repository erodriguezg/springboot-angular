package com.github.erodriguezg.springbootangular.repository;

import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.entities.Persona;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioFiltroDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {


    Usuario findByUsername(String username);

    Usuario findByRun(Integer run);

    Usuario findByEmail(String email);

    // specifications

    default Specification<Usuario> filtroToSpecification(UsuarioFiltroDto filtroDto) {
        return (user, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Path<Persona> persona = user.get("persona");

            addSpecApMaterno(filtroDto, predicates, cb, persona);
            addSpecApPaterno(filtroDto, predicates, cb, persona);
            addSpecEmail(filtroDto, predicates, cb, persona);
            addSpecFechaNacimiento(filtroDto, predicates, cb, persona);
            addSpecHabilitado(filtroDto, predicates, cb, user);
            addSpecNombres(filtroDto, predicates, cb, persona);
            addSpecPerfil(filtroDto, predicates, cb, user);
            addSpecRun(filtroDto, predicates, cb, persona);
            addSpecUsername(filtroDto, predicates, cb, user);

            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }

    default void addSpecApMaterno(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Path<Persona> persona) {
        if(StringUtils.isNotBlank(filtroDto.getApMaterno())) {
            predicates.add(cb.like(persona.get("apellidoMaterno"), "%" + filtroDto.getApMaterno() + "%"));
        }
    }

    default void addSpecApPaterno(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Path<Persona> persona) {
        if(StringUtils.isNotBlank(filtroDto.getApPaterno())) {
            predicates.add(cb.like(persona.get("apellidoPaterno"), "%" + filtroDto.getApPaterno() + "%"));
        }
    }

    default void addSpecEmail(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Path<Persona> persona) {
        if(StringUtils.isNotBlank(filtroDto.getEmail())) {
            predicates.add(cb.like(persona.get("email"), "%" + filtroDto.getEmail() + "%"));
        }
    }

    default void addSpecFechaNacimiento(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Path<Persona> persona) {
        if(filtroDto.getFechaNacimientoInferior() != null) {
            predicates.add(cb.greaterThanOrEqualTo(persona.get("fechaNacimiento"), filtroDto.getFechaNacimientoInferior()));
        }
        if(filtroDto.getFechaNacimientoSuperior() != null) {
            predicates.add(cb.greaterThanOrEqualTo(persona.get("fechaNacimiento"), filtroDto.getFechaNacimientoSuperior()));
        }
    }

    default void addSpecHabilitado(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Root<Usuario> user) {
        if(filtroDto.getHabilitado() != null) {
            predicates.add(cb.equal(user.get("habilitado"), filtroDto.getHabilitado()));
        }
    }

    default void addSpecNombres(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Path<Persona> persona) {
        if(StringUtils.isNotBlank(filtroDto.getNombres())) {
            predicates.add(cb.like(persona.get("nombres"), "%" + filtroDto.getNombres() + "%"));
        }
    }

    default void addSpecPerfil(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Root<Usuario> user) {
        if(filtroDto.getPerfilDto() != null && filtroDto.getPerfilDto().getId() != null) {
            PerfilUsuario perfilUsuario = new PerfilUsuario(filtroDto.getPerfilDto().getId());
            predicates.add(cb.equal(user.get("perfil"), perfilUsuario));
        }
    }

    default void addSpecRun(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Path<Persona> persona) {
        if(filtroDto.getRun() != null) {
            predicates.add(cb.equal(persona.get("run"), filtroDto.getRun()));
        }
    }

    default void addSpecUsername(UsuarioFiltroDto filtroDto, List<Predicate> predicates, CriteriaBuilder cb, Root<Usuario> user) {
        if(StringUtils.isNotBlank(filtroDto.getUsername())) {
            predicates.add(cb.like(user.get("username"), "%" + filtroDto.getNombres() + "%"));
        }
    }

}
