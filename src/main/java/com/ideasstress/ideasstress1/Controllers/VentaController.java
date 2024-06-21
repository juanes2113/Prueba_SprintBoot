package com.ideasstress.ideasstress1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ideasstress.ideasstress1.Models.DAO.IDaoVenta;
import com.ideasstress.ideasstress1.Models.Entity.Venta;
//import org.springframework.web.bind.annotation.RequestMapping;
import com.ideasstress.ideasstress1.Models.Repositorio.VentaRepository;

@Controller
//@RequestMapping(path = "/Venta")

public class VentaController
{
    @Autowired
    VentaRepository ventasRepository;
    @Autowired
    IDaoVenta ventaDao;

    @GetMapping(value = "/Venta")

    public String mostrarVentas(Model model)
    {
        model.addAttribute("titulo", "LISTADO DE VENTAS");
        model.addAttribute("ventas", ventasRepository.findAll());
        
        return "Venta";
    }
    /*
    @GetMapping(value = "/Factura")

    public String Factura(Model model)
    {
        model.addAttribute("titulo", "FACTURA DE VENTA");
        model.addAttribute("ventas", ventasRepository.findAll());
        
        return "Factura";
    }*/

    @GetMapping("/Factura/{Id_venta}")
    
    public String factura(@PathVariable(value = "Id_venta") Long id, Model model){

        Venta venta = new Venta();

        if(id>0)
        {
            venta= ventaDao.searchSale(id);
        }
        else
        {
            return "redirect:/Venta";
        }

        model.addAttribute("titulo", "Factura de venta");
        model.addAttribute("ventas", venta);

        return "Factura";
    }
}
