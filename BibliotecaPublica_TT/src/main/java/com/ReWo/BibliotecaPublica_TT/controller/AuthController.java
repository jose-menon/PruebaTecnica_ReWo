package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.dto.LoginRequest;
import com.ReWo.BibliotecaPublica_TT.dto.LoginResponse;
import com.ReWo.BibliotecaPublica_TT.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
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
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        LoginResponse response = new LoginResponse(
                "Inicio de Sesion Exitoso",
                userDetails.getUsuario().getIdUsuario(),
                userDetails.getUsuario().getEmail(),
                userDetails.getUsuario().getRolUsuario().getNombreRol()
        );
        return ResponseEntity.ok(response);
    }
}
