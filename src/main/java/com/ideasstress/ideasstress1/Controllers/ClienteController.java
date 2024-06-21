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
import com.ideasstress.ideasstress1.Models.DAO.IClienteDao;
import com.ideasstress.ideasstress1.Models.Entity.Cliente;

@Controller
@SessionAttributes("cliente")

public class ClienteController
{
    @Autowired

    private IClienteDao clienteDao;

    @GetMapping("/listar")

    public String listar(Model model)
    {
        model.addAttribute("titulo", "LISTADO DE CLIENTES");
        model.addAttribute("clientes", clienteDao.findAll());

        return "/listar";
    }

    @GetMapping("/form") // Para crear un nuevo usuario

    public String crear(Model model)
    {
        Cliente cliente = new Cliente();

        model.addAttribute("titulo", "FORMULARIO DE CLIENTES");
        model.addAttribute("cliente", cliente);

        return "form";
    }

    //@RequestMapping(value = "/form", method = RequestMethod.POST)
    @PostMapping("/form")

    public String guardar(@Valid Cliente cliente, BindingResult Resultado,SessionStatus status, Model model)
    {
        if(Resultado.hasErrors())
        {
            model.addAttribute("titulo", "FORMULARIO DE CLIENTES");
            model.addAttribute("cliente", cliente);
            
            return "form";
        }

        clienteDao.save(cliente);
        status.setComplete();

        return "redirect:listar";
    }

    @GetMapping("/form/{id}")

    public String editar(@PathVariable(value = "id") Long id, Model model)
    {
        Cliente cliente = new Cliente();

        if(id > 0)
        {
            cliente = clienteDao.findOne(id);
        }
        else
        {
            return "redirect:/Listar";
        }

        model.addAttribute("Titulo", "Editar Clientes");
        model.addAttribute("cliente", cliente);

        return  "form";
    }

    @GetMapping("/eliminar/{id}")

    public String eliminar(@PathVariable Long id)
    {
        if(id > 0)
        {
            clienteDao.delete(id);
        }
        
        return "redirect:/listar";
    }
}