package com.ideasstress.ideasstress1.Models.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ideasstress.ideasstress1.Models.Entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>
{
    
}
