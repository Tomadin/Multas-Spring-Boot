package com.mycompany.IntegradorMVC.repository;

import com.mycompany.IntegradorMVC.model.ActaDeConstatacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActaDeConstatacionRepository extends JpaRepository<ActaDeConstatacion, Integer> {

    boolean existsByAutoridadDeConstatacion_Dni(int dni);

    boolean existsByInfracciones_Id(int id);
}
