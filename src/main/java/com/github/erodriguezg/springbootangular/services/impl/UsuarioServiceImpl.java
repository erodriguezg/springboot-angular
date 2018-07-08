package com.github.erodriguezg.springbootangular.services.impl;

import com.github.erodriguezg.springbootangular.services.UsuarioService;
import com.github.erodriguezg.springbootangular.services.dao.UsuarioDao;
import com.github.erodriguezg.springbootangular.services.dto.PersonaDto;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioFiltroDto;
import com.github.erodriguezg.springbootangular.services.entities.Persona;
import com.github.erodriguezg.springbootangular.services.entities.Usuario;
import com.github.erodriguezg.springbootangular.services.exceptions.LogicaNegocioException;
import com.github.erodriguezg.springbootangular.services.mappers.UsuarioDtoMapper;
import com.github.erodriguezg.springbootangular.utils.ConstantesUtil;
import com.github.erodriguezg.javautils.CodecUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private UsuarioDtoMapper usuarioMapper;

    @Autowired
    private CodecUtils codecUtils;

    @Override
    public List<UsuarioDto> traerTodos() {
        return this.usuarioDao.taerTodos().stream().map(this.usuarioMapper::toUsuarioDto).collect(Collectors.toList());
    }

    @Override
    public UsuarioDto traerPorEmail(final String email) {
        Usuario usuario = usuarioDao.traerPorEmail(email != null ? email.trim().toLowerCase() : null);
        return usuarioMapper.toUsuarioDto(usuario);
    }

    @Transactional(readOnly = false)
    @Override
    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
        LOG.debug("Guardar Usuario: {}", usuarioDto);

        PersonaDto personaDto = usuarioDto.getPersona();

        Usuario usuarioAux = usuarioDao.traerPorEmail(personaDto.getEmail());
        if (usuarioAux != null && !usuarioAux.getIdPersona().equals(usuarioDto.getId())) {
            throw new LogicaNegocioException("Ya existe correo para el usuario");
        }

        usuarioAux = usuarioDao.traerPorRun(personaDto.getRun());
        if (usuarioAux != null && !usuarioAux.getIdPersona().equals(usuarioDto.getId())) {
            throw new LogicaNegocioException("Ya existe run para el usuario");
        }
        else if (usuarioAux == null) {
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

    @Override
    public UsuarioDto traerPorUsername(final String username) {
        Usuario usuario = usuarioDao.traerPorUsername(username != null ? username.trim().toLowerCase() : null);
        return usuarioMapper.toUsuarioDto(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public long buscarRowCount(UsuarioFiltroDto filtroDto) {
        return this.usuarioDao.traerPorFiltroRowCount(filtroDto);
    }

    @Override
    public List<UsuarioDto> buscar(UsuarioFiltroDto filtroDto, int inicio, int fin) {
        List<Usuario> usuarioList = this.usuarioDao.traerPorFiltro(filtroDto, inicio, fin);
        return usuarioList.stream().map(this.usuarioMapper::toUsuarioDto).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDto> buscar(UsuarioFiltroDto usuarioFiltroDto) {
        List<Usuario> usuarioList = this.usuarioDao.traerPorFiltro(usuarioFiltroDto, 0, Integer.MAX_VALUE);
        return usuarioList.stream().map(this.usuarioMapper::toUsuarioDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminar(UsuarioDto usuarioDto, Long idUsuarioActual) {
        if (idUsuarioActual.equals(usuarioDto.getId())) {
            throw new LogicaNegocioException(ConstantesUtil.MSJ_ELIMINAR_A_SI_MISMO);
        }
        Usuario usuario = this.usuarioDao.traerPorId(usuarioDto.getId());
        Persona persona = usuario.getPersona();
        em.remove(usuario);
        em.remove(persona);
    }

    @Transactional(readOnly = false)
    @Override
    public void habilitar(UsuarioDto usuarioDto) {
        Usuario usuario = this.usuarioDao.traerPorId(usuarioDto.getId());
        usuario.setHabilitado(true);
    }

    @Transactional(readOnly = false)
    @Override
    public void deshabilitar(UsuarioDto usuarioDto, Long idUsuarioActual) {
        if (idUsuarioActual.equals(usuarioDto.getId())) {
            throw new LogicaNegocioException(ConstantesUtil.MSJ_DESHABILITAR_A_SI_MISMO);
        }
        Usuario usuario = this.usuarioDao.traerPorId(usuarioDto.getId());
        usuario.setHabilitado(false);
    }

    @Override
    public UsuarioDto traerPorRun(Integer run) {
        Usuario usuario = this.usuarioDao.traerPorRun(run);
        return usuarioMapper.toUsuarioDto(usuario);
    }

    @Override
    public UsuarioDto traerPorId(Long idUsuario) {
        Usuario usuario = this.usuarioDao.traerPorId(idUsuario);
        return usuarioMapper.toUsuarioDto(usuario);
    }

}
