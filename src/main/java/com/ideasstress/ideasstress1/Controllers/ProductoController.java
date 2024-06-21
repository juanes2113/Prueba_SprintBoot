package com.ideasstress.ideasstress1.Controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.ideasstress.ideasstress1.Models.DAO.IProductoDao;
import com.ideasstress.ideasstress1.Models.Entity.Producto;

@Controller
@SessionAttributes("producto")

public class ProductoController
{
    @Autowired

    private IProductoDao productoDao;

    @GetMapping("/listProducts")

    public String listProducts(Model model)
    {
        model.addAttribute("titulo", "LISTA DE PRODUCTOS");
        model.addAttribute("Producto", productoDao.listProducts());

        return "/listProducts";
    }

    @GetMapping("/createProduct") // Para crear un nuevo usuario

    public String createProduct(Model model)
    {
        Producto producto = new Producto();

        model.addAttribute("titulo", "CREAR PRODUCTO");
        model.addAttribute("producto", producto);

        return "createProduct";
    }

    @PostMapping("/createProduct")

    public String save(@Valid Producto producto, BindingResult Resultado, SessionStatus state, Model model)
    {
        if(Resultado.hasErrors())
        {
            model.addAttribute("titulo", "CREAR PRODUCTO");
            model.addAttribute("producto", producto);

            return "createProduct";
        }
        
        productoDao.saveProduct(producto);
        state.setComplete();

        return "redirect:listProducts";
    }

    @GetMapping("/createProduct/{Id_product}")

    public String edit(@PathVariable(value = "Id_product") Long id, Model model)
    {
        Producto producto = new Producto();

        if(id > 0)
        {
            producto = productoDao.searchProduct(id);
        }
        else
        {
            return "redirect:/listProducts";
        }

        model.addAttribute("Titulo", "EDITAR PRODUCTO");
        model.addAttribute("producto", producto);

        return  "createProduct";
    }

    @GetMapping("/eliminarProducto/{Id_product}")

    public String eliminarProducto(@PathVariable Long Id_product)
    {
        if(Id_product > 0)
        {
            productoDao.deleteProduct(Id_product);
        }
        
        return "redirect:/listProducts";
    }   
}
