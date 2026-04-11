package com.ReWo.BibliotecaPublica_TT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id_categoria;
    @Column(name = "nombreCategoria")
    private String nombreCategoria;
    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Libro> listadoLibros = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(Long id_categoria, String nombreCategoria, List<Libro> listadoLibros)
    {
        this.id_categoria = id_categoria;
        this.nombreCategoria = nombreCategoria;
        this.listadoLibros = listadoLibros;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<Libro> getListadoLibros() {
        return listadoLibros;
    }

    public void setListadoLibros(List<Libro> listadoLibros) {
        this.listadoLibros = listadoLibros;
    }
}
