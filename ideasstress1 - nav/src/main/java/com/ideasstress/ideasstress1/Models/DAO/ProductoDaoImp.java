package com.ideasstress.ideasstress1.Models.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ideasstress.ideasstress1.Models.Entity.Producto;

@Repository

public class ProductoDaoImp implements IProductoDao
{
    @PersistenceContext // Entity necesita el contexto de persistencia

    private EntityManager Aux;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override

    public List<Producto> listProducts()
    {
        return Aux.createQuery("from Producto").getResultList();
    }

    @Override
    @Transactional

    public void saveProduct(Producto producto)
    {
        if(producto.getId_product() != null && producto.getId_product() > 0)
        {
            Aux.merge(producto);
        }
        else
        {
            Aux.persist(producto);
        }
    }

    @Override
    @Transactional

    public Producto searchProduct(Long Id_product)
    {
        return Aux.find(Producto.class,Id_product);
    }
 
    @Override
    @Transactional

    public void deleteProduct(Long Id_product)
    {
        Producto producto = searchProduct(Id_product);
        Aux.remove(producto);
    }
}