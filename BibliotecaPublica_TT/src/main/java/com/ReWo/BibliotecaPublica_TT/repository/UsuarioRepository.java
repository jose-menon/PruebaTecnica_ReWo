package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByEdad(Integer edad);

    List<Usuario> findByEdadBetween(Integer edadMin, Integer edadMax);

    List<Usuario> findByApellidoUsuarioContainingIgnoreCase(String apellido);

    List<Usuario> findByNombreUsuarioContainingIgnoreCase(String nombre);
}
