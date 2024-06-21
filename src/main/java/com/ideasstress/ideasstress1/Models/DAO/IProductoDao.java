package com.ideasstress.ideasstress1.Models.DAO;

import java.util.List;
import com.ideasstress.ideasstress1.Models.Entity.Producto;

public interface IProductoDao
{
    public List<Producto> listProducts();
    public void saveProduct(Producto producto);
    public Producto searchProduct(Long Id_product);
    public void deleteProduct(Long Id_product); 
}