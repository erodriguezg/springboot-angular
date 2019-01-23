package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.dto.UsuarioFiltroDto;
import com.github.erodriguezg.springbootangular.entities.Persona;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import com.github.erodriguezg.springbootangular.repository.UsuarioRepository;
import com.github.erodriguezg.springbootangular.services.exceptions.LogicaNegocioException;
import com.github.erodriguezg.springbootangular.utils.ConstantesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> traerTodos() {
        return usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "usuario"));
    }

    public Usuario traerPorEmail(final String email) {
        return usuarioRepository.findByPersonaEmail(email != null ? email.trim().toLowerCase() : null);
    }

    @Transactional(readOnly = false)
    public Usuario guardarUsuario(Usuario usuarioParam) {
        LOG.debug("Guardar Usuario: {}", usuarioParam);

        Persona personaParam = usuarioParam.getPersona();

        Usuario usuarioAux = usuarioRepository.findByPersonaEmail(personaParam.getEmail());
        if (usuarioAux != null && !usuarioAux.getIdPersona().equals(usuarioParam.getIdPersona())) {
            throw new LogicaNegocioException("Ya existe correo para el usuario");
        }

        usuarioAux = usuarioRepository.findByPersonaRun(personaParam.getRun());
        if (usuarioAux != null && !usuarioAux.getIdPersona().equals(usuarioParam.getIdPersona())) {
            throw new LogicaNegocioException("Ya existe run para el usuario");
        } else if (usuarioAux == null) {
            usuarioParam.setHabilitado(true);
        }

        if (usuarioAux == null || !usuarioAux.getPassword().equals(usuarioParam.getPassword())) {
            usuarioParam.setPassword(passwordEncoder.encode(usuarioParam.getPassword()));
        }
        Usuario usuario = usuarioParam;
        Persona persona = em.merge(usuario.getPersona());
        persona.setUsuario(usuario);
        usuario.setPersona(persona);
        if (usuarioAux == null) {
            em.persist(usuario);
        } else {
            usuario.setHabilitado(usuarioAux.getHabilitado());
            usuario = em.merge(usuario);
        }
        return usuario;
    }

    public Usuario traerPorUsername(final String username) {
        return usuarioRepository.findByUsername(username != null ? username.trim().toLowerCase() : null);
    }

    public long buscarRowCount(UsuarioFiltroDto filtroDto) {
        Specification<Usuario> userSpecification = usuarioRepository.filtroToSpecification(filtroDto);
        return usuarioRepository.count(userSpecification);
    }

    public List<Usuario> buscar(UsuarioFiltroDto filtroDto, int inicio, int fin) {
        Specification<Usuario> userSpecification = usuarioRepository.filtroToSpecification(filtroDto);
        int pageSize = fin - inicio;
        int paginaActual = inicio / pageSize;
        return usuarioRepository.findAll(userSpecification, PageRequest.of(paginaActual, pageSize))
                .getContent();
    }

    public List<Usuario> buscar(UsuarioFiltroDto usuarioFiltroDto) {
        return buscar(usuarioFiltroDto, 0, Integer.MAX_VALUE);
    }

    @Transactional(readOnly = false)
    public void eliminar(Usuario usuarioParam, Long idUsuarioActual) {
        if (idUsuarioActual.equals(usuarioParam.getIdPersona())) {
            throw new LogicaNegocioException(ConstantesUtil.MSJ_ELIMINAR_A_SI_MISMO);
        }
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioParam.getIdPersona());
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Persona persona = usuario.getPersona();
            em.remove(usuario);
            em.remove(persona);
        }
    }

    @Transactional(readOnly = false)
    public void habilitar(Usuario usuarioParam) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioParam.getIdPersona());
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setHabilitado(true);
        }
    }

    @Transactional(readOnly = false)
    public void deshabilitar(Usuario usuarioPAram, Long idUsuarioActual) {
        if (idUsuarioActual.equals(usuarioPAram.getIdPersona())) {
            throw new LogicaNegocioException(ConstantesUtil.MSJ_DESHABILITAR_A_SI_MISMO);
        }
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioPAram.getIdPersona());
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setHabilitado(false);
        }
    }

    public Usuario traerPorRun(Integer run) {
        return usuarioRepository.findByPersonaRun(run);
    }

    public Usuario traerPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

}
