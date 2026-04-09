package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.entity.Usuario;
import com.ReWo.BibliotecaPublica_TT.repository.UsuarioRepository;
import com.ReWo.BibliotecaPublica_TT.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService
{
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
        if(usuarioRepository.existsByEmail(usuario.getEmail()))
        {
            throw new RuntimeException("El email ya se encuentra asociado a un Usuario");
        }
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

        return usuarioRepository.save(usuario);
    }
    @Override
    public void eliminar(Long idUsuario)
    {
        Usuario usuario = buscarPorId(idUsuario);
        usuarioRepository.delete(usuario);
    }
}
