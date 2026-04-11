package com.ReWo.BibliotecaPublica_TT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "libros")
public class Libro
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "autor")
    private String autor;
    @Column(name = "ejemplaresTotales")
    private Integer ejemplaresTotales;
    @Column(name = "ejemplaresDisponibles")
    private Integer ejemplaresDisponibles;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @JsonIgnore
    @OneToMany(mappedBy = "libro")
    private List<Prestamo> listadoPrestamos = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "libro")
    private List<Reserva> listadoReservas = new ArrayList<>();

    public Libro() {
    }

    public Libro(Long idLibro, String titulo, String isbn, String autor, Integer ejemplaresTotales, Integer ejemplaresDisponibles, Categoria categoria, List<Prestamo> listadoPrestamos, List<Reserva> listadoReservas)
    {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.ejemplaresTotales = ejemplaresTotales;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.categoria = categoria;
        this.listadoPrestamos = listadoPrestamos;
        this.listadoReservas = listadoReservas;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getEjemplaresTotales() {
        return ejemplaresTotales;
    }

    public void setEjemplaresTotales(Integer ejemplaresTotales) {
        this.ejemplaresTotales = ejemplaresTotales;
    }

    public Integer getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(Integer ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Prestamo> getListadoPrestamos() {
        return listadoPrestamos;
    }

    public void setListadoPrestamos(List<Prestamo> listadoPrestamos) {
        this.listadoPrestamos = listadoPrestamos;
    }

    public List<Reserva> getListadoReservas() {
        return listadoReservas;
    }

    public void setListadoReservas(List<Reserva> listadoReservas) {
        this.listadoReservas = listadoReservas;
    }
}
