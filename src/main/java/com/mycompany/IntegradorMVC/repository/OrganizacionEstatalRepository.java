package com.mycompany.IntegradorMVC.repository;

import com.mycompany.IntegradorMVC.model.OrganizacionEstatal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacionEstatalRepository extends JpaRepository<OrganizacionEstatal, Integer> {
}
