package com.ideasstress.ideasstress1.Models.DAO;

import java.util.List;
import com.ideasstress.ideasstress1.Models.Entity.Cliente;

public interface IClienteDao
{
    public List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);
}
