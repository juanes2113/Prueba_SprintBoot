package com.ideasstress.ideasstress1.Models.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // Entidad que a estar conectada a los datos (Clase)
@Table(name = "Productos")

public class Producto implements Serializable
{
    @Id // Primary key (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto, Sequence, table

    private Long Id_product; // Long es la clase (int)

    @NotEmpty (message = "Nombre invalido o vacío")
    @Size(min = 4, max = 30)
    private String Nombre;

    @NotEmpty (message = "Descripción invalido o vacío")
    private String Descripcion;

    @NotNull (message = "Las existenicas no pueden ser cero")
    @DecimalMin("1")
    private Float Existencias;

    @NotNull (message = "Debe contener un valor")
    @DecimalMin("9999.99")
    @DecimalMax("999999.99")
    private Float Precio;

    @NotNull(message = "Debes especificar el código")
    @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50")
    private String codigo;

    public Producto() {
    }

    public Producto(Long id_product,
            @NotEmpty(message = "Nombre invalido o vacío") @Size(min = 4, max = 30) String nombre,
            @NotEmpty(message = "Descripción invalido o vacío") String descripcion,
            @NotNull(message = "Las existenicas no pueden ser cero") @DecimalMin("1") Float existencias,
            @NotNull(message = "Debe contener un valor") @DecimalMin("9999.99") @DecimalMax("999999.99") Float precio,
            @NotNull(message = "Debes especificar el código") @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50") String codigo) {
        Id_product = id_product;
        Nombre = nombre;
        Descripcion = descripcion;
        Existencias = existencias;
        Precio = precio;
        this.codigo = codigo;
    }

    public Producto(@NotEmpty(message = "Nombre invalido o vacío") @Size(min = 4, max = 30) String nombre,
            @NotEmpty(message = "Descripción invalido o vacío")String descripcion,
            @NotNull(message = "Las existenicas no pueden ser cero") @DecimalMin("1") Float existencias,
            @NotNull(message = "Debe contener un valor") @DecimalMin("9999.99") @DecimalMax("999999.99") Float precio,
            @NotNull(message = "Debes especificar el código") @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50") String codigo) {
        Nombre = nombre;
        Descripcion = descripcion;
        Existencias = existencias;
        Precio = precio;
        this.codigo = codigo;
    }

    public Producto(
            @NotNull(message = "Debes especificar el código") @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50") String codigo) {
        this.codigo = codigo;
    }

    public Long getId_product() {
        return Id_product;
    }
    public void setId_product(Long id_product) {
        Id_product = id_product;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public Float getExistencias() {
        return Existencias;
    }
    public void setExistencias(Float existencias) {
        Existencias = existencias;
    }
    public Float getPrecio() {
        return Precio;
    }
    public void setPrecio(Float precio) {
        Precio = precio;
    }

    public boolean sinExistencia() {
        return this.Existencias <= 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void restarExistencia(Float float1) {
        this.Existencias -= float1;
    }
}