package com.ReWo.BibliotecaPublica_TT.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest
{
    @NotBlank(message = "El email es un campo obligatorio")
    @Email(message = "El email debe tener un formato valido")
    private String email;
    @NotBlank(message = "la contraseña es obligatoria")
    private String password;
}
