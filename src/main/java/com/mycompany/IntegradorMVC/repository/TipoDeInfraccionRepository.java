package com.mycompany.IntegradorMVC.repository;

import com.mycompany.IntegradorMVC.model.TipoDeInfraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDeInfraccionRepository extends JpaRepository<TipoDeInfraccion, Integer> {
}
