package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.javautils.CodecUtils;
import com.github.erodriguezg.springbootangular.entities.Persona;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import com.github.erodriguezg.springbootangular.repository.UsuarioRepository;
import com.github.erodriguezg.springbootangular.services.dto.PersonaDto;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioFiltroDto;
import com.github.erodriguezg.springbootangular.services.exceptions.LogicaNegocioException;
import com.github.erodriguezg.springbootangular.services.mappers.UsuarioDtoMapper;
import com.github.erodriguezg.springbootangular.utils.ConstantesUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDtoMapper usuarioMapper;

    @Autowired
    private CodecUtils codecUtils;

    public List<UsuarioDto> traerTodos() {
        return usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "usuario"))
                .stream()
                .map(this.usuarioMapper::toUsuarioDto).collect(Collectors.toList());
    }

    public UsuarioDto traerPorEmail(final String email) {
        Usuario usuario = usuarioRepository.findByEmail(email != null ? email.trim().toLowerCase() : null);
        return usuarioMapper.toUsuarioDto(usuario);
    }

    @Transactional(readOnly = false)
    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
        LOG.debug("Guardar Usuario: {}", usuarioDto);

        PersonaDto personaDto = usuarioDto.getPersona();

        Usuario usuarioAux = usuarioRepository.findByEmail(personaDto.getEmail());
        if (usuarioAux != null && !usuarioAux.getIdPersona().equals(usuarioDto.getId())) {
            throw new LogicaNegocioException("Ya existe correo para el usuario");
        }

        usuarioAux = usuarioRepository.findByRun(personaDto.getRun());
        if (usuarioAux != null && !usuarioAux.getIdPersona().equals(usuarioDto.getId())) {
            throw new LogicaNegocioException("Ya existe run para el usuario");
        } else if (usuarioAux == null) {
            usuarioDto.setHabilitado(true);
        }

        if (usuarioAux == null || !usuarioAux.getPassword().equals(usuarioDto.getPassword())) {
            usuarioDto.setPassword(codecUtils.generarHash(CodecUtils.TypeHash.MD5, usuarioDto.getPassword()));
        }
        Usuario usuario = usuarioMapper.toUsuario(usuarioDto);
        Persona persona = em.merge(usuario.getPersona());
        persona.setUsuario(usuario);
        usuario.setPersona(persona);
        if (usuarioAux == null) {
            em.persist(usuario);
        } else {
            usuario = em.merge(usuario);
        }
        return usuarioMapper.toUsuarioDto(usuario);
    }

    public UsuarioDto traerPorUsername(final String username) {
        Usuario usuario = usuarioRepository.findByUsername(username != null ? username.trim().toLowerCase() : null);
        return usuarioMapper.toUsuarioDto(usuario);
    }

    public long buscarRowCount(UsuarioFiltroDto filtroDto) {
        Specification<Usuario> userSpecification = filtroToSpecification(filtroDto);
        return usuarioRepository.count(userSpecification);
    }

    private Specification<Usuario> filtroToSpecification(UsuarioFiltroDto filtroDto) {
        return (user, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Path<Persona> persona = user.get("persona");
            if(StringUtils.isNotBlank(filtroDto.getApMaterno())) {
                predicates.add(cb.like(persona.get("apellidoMaterno"), "%" + filtroDto.getApMaterno() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }
    public List<UsuarioDto> buscar(UsuarioFiltroDto filtroDto, int inicio, int fin) {
        Specification<Usuario> userSpecification = filtroToSpecification(filtroDto);
        return usuarioRepository.findAll(userSpecification, PageRequest.of(inicio, fin - inicio))
                .stream()
                .map(usuario -> usuarioMapper.toUsuarioDto(usuario))
                .collect(Collectors.toList());
    }

    public List<UsuarioDto> buscar(UsuarioFiltroDto usuarioFiltroDto) {
        return buscar(usuarioFiltroDto, 0, Integer.MAX_VALUE);
    }

    @Transactional(readOnly = false)
    public void eliminar(UsuarioDto usuarioDto, Long idUsuarioActual) {
        if (idUsuarioActual.equals(usuarioDto.getId())) {
            throw new LogicaNegocioException(ConstantesUtil.MSJ_ELIMINAR_A_SI_MISMO);
        }
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioDto.getId());
        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Persona persona = usuario.getPersona();
            em.remove(usuario);
            em.remove(persona);
        }
    }

    @Transactional(readOnly = false)
    public void habilitar(UsuarioDto usuarioDto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioDto.getId());
        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setHabilitado(true);
        }
    }

    @Transactional(readOnly = false)
    public void deshabilitar(UsuarioDto usuarioDto, Long idUsuarioActual) {
        if (idUsuarioActual.equals(usuarioDto.getId())) {
            throw new LogicaNegocioException(ConstantesUtil.MSJ_DESHABILITAR_A_SI_MISMO);
        }
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioDto.getId());
        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setHabilitado(false);
        }
    }

    public UsuarioDto traerPorRun(Integer run) {
        Usuario usuario = usuarioRepository.findByRun(run);
        return usuarioMapper.toUsuarioDto(usuario);
    }

    public UsuarioDto traerPorId(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        return usuarioMapper.toUsuarioDto(usuario);
    }

}
