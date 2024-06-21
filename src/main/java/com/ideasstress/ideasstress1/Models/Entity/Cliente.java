package com.ideasstress.ideasstress1.Models.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity//entidad que va a estar conectada a los datos 
@Table(name = "clientes")  //Nombre de la tabla en la base de datos

public class Cliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    @NotEmpty (message = "Nombre invalido o vacío")
    @Size(min = 4, max = 30)
    private String Nombre;

    @NotEmpty (message = "Apellido invalido o vacío")
    @Size(min = 4, max = 30)
    private String Apellido;

    @Email
    private String email;

    @Column(name = "creat_at") // @Column para definir el nombre de la columna.
    @Temporal (TemporalType.DATE) // Para determinar si el atributo almacena Hora, fecha o fecha y hora (.Date .Time .Timestamp)
    @DateTimeFormat(pattern = "yyyy-MM-dd") 

    @Past (message = "Fecha invalida")
    @NotNull
    private Date CreateAt;//se usa el date de java.util
    /*
    @PrePersist

    public void PrePersist()
    {
        CreateAt = new Date();
    }
     */
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }
}