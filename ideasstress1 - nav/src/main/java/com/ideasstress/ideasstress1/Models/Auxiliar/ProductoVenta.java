package com.ideasstress.ideasstress1.Models.Auxiliar;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.ideasstress.ideasstress1.Models.Entity.Producto;

public class ProductoVenta extends Producto
{
    private Float Cantidad;

    public ProductoVenta(Long id_product,
            @NotEmpty(message = "Nombre invalido o vacío") @Size(min = 4, max = 30) String nombre,
            @NotEmpty(message = "Descripción invalido o vacío") String descripcion,
            @NotNull(message = "Las existenicas no pueden ser cero") @DecimalMin("1") Float existencias,
            @NotNull(message = "Debe contener un valor") @DecimalMin("9999.99") @DecimalMax("999999.99") Float precio,
            @NotNull(message = "Debes especificar el código") @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50") String codigo,
            Float cantidad) {
        super(id_product, nombre, descripcion, existencias, precio, codigo);
        Cantidad = cantidad;
    }

    public ProductoVenta(@NotEmpty(message = "Nombre invalido o vacío") @Size(min = 4, max = 30) String nombre,
            @NotEmpty(message = "Descripción invalido o vacío") String descripcion,
            @NotNull(message = "Las existenicas no pueden ser cero") @DecimalMin("1") Float existencias,
            @NotNull(message = "Debe contener un valor") @DecimalMin("9999.99") @DecimalMax("999999.99") Float precio,
            @NotNull(message = "Debes especificar el código") @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50") String codigo,
            Float cantidad) {
        super(nombre, descripcion, existencias, precio, codigo);
        Cantidad = cantidad;
    }

    public void aumentarCantidad() {
        this.Cantidad++;
    }

    public Float getCantidad() {
        return Cantidad;
    }

    public Float getTotal() {
        return this.getPrecio() * this.Cantidad;
    }
}
