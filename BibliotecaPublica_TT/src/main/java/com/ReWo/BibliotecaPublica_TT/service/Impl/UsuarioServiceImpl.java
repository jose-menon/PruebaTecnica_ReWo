package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.entity.Rol;
import com.ReWo.BibliotecaPublica_TT.entity.Usuario;
import com.ReWo.BibliotecaPublica_TT.exception.BusinessException;
import com.ReWo.BibliotecaPublica_TT.exception.ResoureNotFoundException;
import com.ReWo.BibliotecaPublica_TT.repository.RolRepository;
import com.ReWo.BibliotecaPublica_TT.repository.UsuarioRepository;
import com.ReWo.BibliotecaPublica_TT.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService
{
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, RolRepository rolRepository)
    {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Usuario> listarTodos()
    {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario buscarPorId(Long idUsuario)
    {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Id del Usuario Inexistente, ver Id: " + idUsuario));
    }

    @Override
    public Usuario buscarPorEmail(String email)
    {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("El email: " + email + " no se encuentra"));
    }

    @Override
    public Usuario guardar(Usuario usuario)
    {
        Rol rol = rolRepository.findById(usuario.getRolUsuario().getId_rol())
                .orElseThrow(() -> new ResoureNotFoundException("Rol no encontrado o Inexistente"));
        usuario.setRolUsuario(rol);
        if(usuarioRepository.existsByEmail(usuario.getEmail()))
        {
            throw new BusinessException("El email ya se encuentra asociado a un Usuario");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
    @Override
    public Usuario actualizar(Long idUsuario, Usuario usuarioActualizado)
    {
        Usuario usuario = buscarPorId(idUsuario);

        usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
        usuario.setApellidoUsuario(usuarioActualizado.getApellidoUsuario());
        usuario.setEmail(usuarioActualizado.getEmail());
        usuario.setEdad(usuarioActualizado.getEdad());
        usuario.setPassword(usuarioActualizado.getPassword());
        usuario.setRolUsuario(usuarioActualizado.getRolUsuario());

        if(usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isBlank())
        {
            usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }

        return usuarioRepository.save(usuario);
    }
    @Override
    public void eliminar(Long idUsuario)
    {
        Usuario usuario = buscarPorId(idUsuario);
        usuarioRepository.delete(usuario);
    }
}
