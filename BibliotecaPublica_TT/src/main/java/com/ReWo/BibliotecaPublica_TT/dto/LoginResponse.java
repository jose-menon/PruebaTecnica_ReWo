package com.ReWo.BibliotecaPublica_TT.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse
{
    private String mensaje;
    private Long idUsuario;
    private String email;
    private String rol;
}
