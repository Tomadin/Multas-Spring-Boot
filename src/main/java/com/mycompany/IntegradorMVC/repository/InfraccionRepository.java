package com.mycompany.IntegradorMVC.repository;

import com.mycompany.IntegradorMVC.model.Infraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfraccionRepository extends JpaRepository<Infraccion, Long> {
}
