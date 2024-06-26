package com.ideasstress.ideasstress1.Models.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ideasstress.ideasstress1.Models.Entity.Cliente;

@Repository

public class ClienteDaoImp implements IClienteDao
{
    @PersistenceContext // Entity necesita el contexto de persistencia

    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override

    public List<Cliente> findAll()
    {
        return em.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional

    public void save(Cliente cliente)
    {
        if(cliente.getId() != null && cliente.getId() > 0)
        {
            em.merge(cliente);
        }
        else
        {
            em.persist(cliente);
        }
    }

    @Override
    @Transactional

    public Cliente findOne(Long id)
    {
        return em.find(Cliente.class,id);
    }
 
    @Override
    @Transactional

    public void delete(Long id)
    {
        Cliente cliente = findOne(id);
        em.remove(cliente);
    }

}