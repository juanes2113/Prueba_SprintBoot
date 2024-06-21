package com.ideasstress.ideasstress1.Models.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos_vendidos")

public class ProductoVendido
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_Producto;

    private String Nombre;
    private String Descripcion;
    private Float Cantidad;
    private Float Precio;
    private String codigo;

    @ManyToOne
    @JoinColumn
    private Venta venta;

    public ProductoVendido(String nombre, String descripcion, Float float1, Float precio, String codigo, Venta venta) {
        Nombre = nombre;
        Descripcion = descripcion;
        Cantidad = float1;
        Precio = precio;
        this.codigo = codigo;
        this.venta = venta;
    }

    public ProductoVendido() {
    }

    public ProductoVendido(Long id_product, String nombre2, String descripcion2, Float cantidad2, Float precio2,
            String codigo2, Venta v) {
    }

    public ProductoVendido(Float cantidad2, Float precio2, String nombre2, String codigo2, Venta v) {
    }

    public Float getTotal() {
        return this.Cantidad * this.Precio;
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

    public Float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Float cantidad) {
        Cantidad = cantidad;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float precio) {
        Precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    } 
}
