package com.mycompany.IntegradorMVC.repository;

import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoridadDeConstatacionRepository extends JpaRepository<AutoridadDeConstatacion, Integer> {

    boolean existsByDni(int dni);

    Optional<AutoridadDeConstatacion> findByDni(int dni);
}
