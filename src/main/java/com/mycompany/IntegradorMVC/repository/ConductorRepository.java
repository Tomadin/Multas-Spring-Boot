package com.mycompany.IntegradorMVC.repository;

import com.mycompany.IntegradorMVC.model.Conductor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Integer> {

    Optional<Conductor> findByDni(int dni);
}
