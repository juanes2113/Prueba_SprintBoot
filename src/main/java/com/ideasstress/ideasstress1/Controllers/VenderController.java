package com.ideasstress.ideasstress1.Controllers;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ideasstress.ideasstress1.Models.Auxiliar.ProductoVenta;
import com.ideasstress.ideasstress1.Models.Entity.Producto;
import com.ideasstress.ideasstress1.Models.Entity.ProductoVendido;
import com.ideasstress.ideasstress1.Models.Entity.Venta;
import com.ideasstress.ideasstress1.Models.Repositorio.ProductoRepository;
import com.ideasstress.ideasstress1.Models.Repositorio.ProductoVendidoRepository;
import com.ideasstress.ideasstress1.Models.Repositorio.VentaRepository;

@Controller

public class VenderController
{
    @Autowired
    private ProductoRepository Aux;
    @Autowired
    private VentaRepository Aux2;
    @Autowired
    private ProductoVendidoRepository Aux3;

    private ArrayList<ProductoVenta> obtenerCarrito(HttpServletRequest request)
    {
        ArrayList<ProductoVenta> carrito = (ArrayList<ProductoVenta>) request.getSession().getAttribute("carrito");
        
        if (carrito == null)
        {
            carrito = new ArrayList<>();
        }

        return carrito;
    }
    
    private void guardarCarrito(ArrayList<ProductoVenta> carrito, HttpServletRequest request)
    {
        request.getSession().setAttribute("carrito", carrito);
    }

    @GetMapping(value = "/Vender")
    
    public String Vender(Model model, HttpServletRequest request)
    {
        model.addAttribute("titulo", "PROCESO DE VENTA");
        model.addAttribute("producto", new Producto());
        
        float total = 0;
    
        ArrayList<ProductoVenta> carrito = this.obtenerCarrito(request);
    
        for (ProductoVenta p: carrito) total += p.getTotal();
    
        model.addAttribute("total", total);
    
        return "Vender";
    }

    @PostMapping(value = "/Agregar")
    
    public String Agregar_Carrito(@ModelAttribute Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs)
    {
        ArrayList<ProductoVenta> Carrito = this.obtenerCarrito(request);

        Producto producto_Codigo = Aux.findBycodigo(producto.getCodigo());

        if (producto_Codigo == null)
        {
            redirectAttrs
                .addFlashAttribute("mensaje", "El producto con el código " + producto.getCodigo() + " no existe")
                .addFlashAttribute("clase", "warning");
        
                return "redirect:/Vender";
        }
        
        if (producto_Codigo.sinExistencia())
        {
            redirectAttrs
                .addFlashAttribute("mensaje", "El producto está agotado")
                .addFlashAttribute("clase", "warning");
            
            return "redirect:/Vender";
        }

        boolean Encontrado = false;

        for (ProductoVenta productoParaVenderActual: Carrito)
        {
            if (productoParaVenderActual.getCodigo().equals(producto_Codigo.getCodigo()))
            {
                productoParaVenderActual.aumentarCantidad();
                Encontrado = true;
                
                break;
            }
        }
    
        if (!Encontrado)
        {
            Carrito.add(new ProductoVenta(producto_Codigo.getId_product(), producto_Codigo.getNombre(), producto_Codigo.getDescripcion(), producto_Codigo.getExistencias(), producto_Codigo.getPrecio(), producto_Codigo.getCodigo(), 1f));
        }
    
        this.guardarCarrito(Carrito, request);
    
        return "redirect:/Vender";
    }

    @PostMapping(value = "/Quitar/{indice}")

    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request)
    {
        ArrayList<ProductoVenta> carrito = this.obtenerCarrito(request);
    
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null)
        {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
    
        return "redirect:/Vender";
    }

    private void limpiarCarrito(HttpServletRequest request)
    {
        this.guardarCarrito(new ArrayList<>(), request);
    }
    
    @GetMapping(value = "/Limpiar")

    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs)
    {
        this.limpiarCarrito(request);
        
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");

        return "redirect:/Vender";
    }

    @PostMapping(value = "/Terminar")
    
    public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) 
    {
        ArrayList<ProductoVenta> carrito = this.obtenerCarrito(request);

        // Si no hay carrito o está vacío, regresamos inmediatamente

        if (carrito == null || carrito.size() <= 0)
        {
            return "redirect:/Vender";
        }

        Venta v = Aux2.save(new Venta());

        // Recorrer el carrito

        for (ProductoVenta productoParaVender : carrito)
        {
            // Obtener el producto fresco desde la base de datos

            Producto p = Aux.findById(productoParaVender.getId_product()).orElse(null);

            if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
            
            // Le restamos existencia
            
            p.restarExistencia(productoParaVender.getCantidad());
            
            // Lo guardamos con la existencia ya restada
            
            Aux.save(p);
            
            // Creamos un nuevo producto que será el que se guarda junto con la venta
            
            ProductoVendido productovendido = new ProductoVendido(productoParaVender.getNombre(),productoParaVender.getDescripcion(),productoParaVender.getCantidad(),productoParaVender.getPrecio(),productoParaVender.getCodigo(),v);
            
            // Y lo guardamos
            
            Aux3.save(productovendido);
        }

        // Al final limpiamos el carrito
       
        this.limpiarCarrito(request);
       
        // e indicamos una venta exitosa
        
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        
        return "redirect:/Vender";
    }
}