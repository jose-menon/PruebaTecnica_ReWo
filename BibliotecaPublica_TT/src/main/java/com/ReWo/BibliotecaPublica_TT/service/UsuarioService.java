package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.entity.Usuario;

import java.util.List;

public interface UsuarioService
{
    List<Usuario> listarTodos();

    Usuario buscarPorId(Long idUsuario);

    Usuario buscarPorEmail(String email);

    Usuario guardar(Usuario usuario);

    Usuario actualizar(Long idUsuario, Usuario usuarioActualizado);

    void eliminar(Long idUsuario);
}
