package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.dto.LoginRequest;
import com.ReWo.BibliotecaPublica_TT.dto.LoginResponse;
import com.ReWo.BibliotecaPublica_TT.entity.Usuario;
import com.ReWo.BibliotecaPublica_TT.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController
{
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserDetailsImpl userDetails)) {
            throw new RuntimeException("El principal autenticado no tiene el tipo esperado");
        }

        Usuario usuario = userDetails.getUsuario();

        if (usuario == null) {
            throw new RuntimeException("No se pudo obtener el usuario autenticado");
        }

        if (usuario.getRolUsuario() == null || usuario.getRolUsuario().getNombreRol() == null) {
            throw new RuntimeException("El usuario autenticado no tiene un rol válido");
        }

        LoginResponse response = new LoginResponse(
                "Inicio de Sesion Exitoso",
                usuario.getIdUsuario(),
                usuario.getEmail(),
                usuario.getRolUsuario().getNombreRol()
        );

        return ResponseEntity.ok(response);
    }
}
