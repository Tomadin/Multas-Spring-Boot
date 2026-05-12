package com.mycompany.IntegradorMVC.repository;

import com.mycompany.IntegradorMVC.model.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Integer> {
}
