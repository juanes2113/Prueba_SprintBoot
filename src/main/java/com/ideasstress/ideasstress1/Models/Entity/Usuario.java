package com.ideasstress.ideasstress1.Models.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.Id;

@Entity//entidad que va a estar conectada a los datos 
@Table(name = "usuarios")  //Nombre de la tabla en la base de datos

public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    @Email
    private String email;
    
    private String Password;

    @NotEmpty (message = "Nombre invalido o vacío")
    @Size(min = 4, max = 30)
    private String Nombre;

    @NotEmpty (message = "Apellido invalido o vacío")
    @Size(min = 4, max = 30)
    private String Apellido;

    public Long getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
}
