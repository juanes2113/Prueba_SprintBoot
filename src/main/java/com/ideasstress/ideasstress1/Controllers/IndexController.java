package com.ideasstress.ideasstress1.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.ideasstress.ideasstress1.Models.Entity.Usuario;
import com.ideasstress.ideasstress1.Models.Repositorio.UserRepository;

@Controller

public class IndexController
{
    @Autowired
    private UserRepository Aux; 

    @GetMapping("/login")

    public String login(Model model)
    {
        model.addAttribute("titulo", "INICIAR SESIÓN");

        return "login";
    }

    @GetMapping("/logout")

    public String logout()
    {
        return "login";
    }

    @GetMapping("/Base")

    public String Base1(Model model)
    {
        return "Plantilla/Base";
    }

    @GetMapping("/Index")

    public String Home(Model model)
    {
        model.addAttribute("titulo", "BIENVENIDO");

        return "Index";
    }

    @GetMapping("/formRegistro")
    
    public String Registro(Model model)
    {
        model.addAttribute("titulo", "FORMULARIO DE REGISTRO");
        model.addAttribute("usuario", new Usuario());

        return "formRegistro";
    }

    @PostMapping("/proceso_registro")
    
    public String Proceso_Registro(Usuario usuario,Model model)
    {
        model.addAttribute("titulo", "REGISTRO EXITOSO");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        Aux.save(usuario);

        return "Registro_Exitoso";
    }

    @GetMapping("/list_users")

    public String Lista_Usuarios(Model model)
    {
        model.addAttribute("titulo", "LISTA DE USUARIOS");

        List<Usuario> listaUsurios = Aux.findAll();

        model.addAttribute("listUsers", listaUsurios);

        return "Usuarios";
    }
}