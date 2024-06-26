package com.ideasstress.ideasstress1.Models.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ideasstress.ideasstress1.Models.Entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>
{
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String email);
}
