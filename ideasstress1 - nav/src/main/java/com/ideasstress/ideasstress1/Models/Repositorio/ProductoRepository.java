package com.ideasstress.ideasstress1.Models.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ideasstress.ideasstress1.Models.Entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>
{
    Producto findBycodigo(String codigo);
}
