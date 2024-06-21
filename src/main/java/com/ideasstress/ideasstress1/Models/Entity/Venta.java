package com.ideasstress.ideasstress1.Models.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity
@Table(name = "ventas")

public class Venta implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id_Venta;
    private String Fecha;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<ProductoVendido> productos;

    public Venta() {
        Fecha = obtenerFechaYHoraActual();
    }

    private String obtenerFechaYHoraActual() {
        String formato = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
		LocalDateTime ahora = LocalDateTime.now();
		return formateador.format(ahora);
    }

    public Long getId_Venta() {
        return Id_Venta;
    }

    public void setId_Venta(Long id_Venta) {
        Id_Venta = id_Venta;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Set<ProductoVendido> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductoVendido> productos) {
        this.productos = productos;
    }

    public Float getTotal() {
        
        Float total = 0f;

        for (ProductoVendido productoVendido : this.productos)
        {
            total += productoVendido.getTotal();
        }

        return total;
    }
}
