package com.ideasstress.ideasstress1.Models.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.ideasstress.ideasstress1.Models.Entity.Venta;

@Repository

public class VentaDaoImp implements IDaoVenta
{
    @PersistenceContext
    private EntityManager Aux;

    @Override
    public Venta searchSale(Long Id_Venta)
    {
        return Aux.find(Venta.class,Id_Venta);
    } 
}
