package com.mycompany.IntegradorMVC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "organizacion")
public class OrganizacionEstatal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreOrganizacion;
    private String localidad;

    public OrganizacionEstatal() {
    }

    public OrganizacionEstatal(String nombreOrganizacion, String localidad) {
        this.nombreOrganizacion = nombreOrganizacion;
        this.localidad = localidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return id + " - " + nombreOrganizacion + " - " + localidad;
    }
}
