package com.ReWo.BibliotecaPublica_TT.exception;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String mensaje)
    {
        super(mensaje);
    }
}
