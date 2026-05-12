package com.mycompany.IntegradorMVC.repository;

import com.mycompany.IntegradorMVC.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
}
