package com.ReWo.BibliotecaPublica_TT.security;

import com.ReWo.BibliotecaPublica_TT.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails
{
    private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = Objects.requireNonNull(usuario, "Usuario no puede ser null");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (usuario.getRolUsuario() == null) {
            throw new IllegalStateException("El usuario no tiene rol asignado");
        }

        String nombreRol = usuario.getRolUsuario().getNombreRol();

        if (nombreRol == null || nombreRol.isBlank()) {
            throw new IllegalStateException("El nombre del rol es inválido");
        }

        return List.of(new SimpleGrantedAuthority("ROLE_" + nombreRol));
    }

    @Override
    public String getPassword() {
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalStateException("La contraseña del usuario es inválida");
        }
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalStateException("El email del usuario es inválido");
        }
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}