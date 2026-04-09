package com.ReWo.BibliotecaPublica_TT.entity;

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
    private long id_libro;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "autor")
    private String autor;
    @Column(name = "ejemplaresTotales")
    private int ejemplaresTotales;
    @Column(name = "ejemplaresDisponibles")
    private int ejemplaresDisponibles;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @OneToMany(mappedBy = "libro")
    private List<Prestamo> listadoPrestamos = new ArrayList<>();
    @OneToMany(mappedBy = "libro")
    private List<Reserva> listadoReservas = new ArrayList<>();

    public Libro() {
    }

    public Libro(long id_libro, String titulo, String isbn, String autor, int ejemplaresTotales, int ejemplaresDisponibles, Categoria categoria, List<Prestamo> listadoPrestamos, List<Reserva> listadoReservas)
    {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.ejemplaresTotales = ejemplaresTotales;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.categoria = categoria;
        this.listadoPrestamos = listadoPrestamos;
        this.listadoReservas = listadoReservas;
    }

    public long getId_libro() {
        return id_libro;
    }

    public void setId_libro(long id_libro) {
        this.id_libro = id_libro;
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

    public int getEjemplaresTotales() {
        return ejemplaresTotales;
    }

    public void setEjemplaresTotales(int ejemplaresTotales) {
        this.ejemplaresTotales = ejemplaresTotales;
    }

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
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
