package com.mycompany.IntegradorMVC.dto.request;

import com.mycompany.IntegradorMVC.model.Infraccion;

public class InfraccionRequestDTO {

    private String descripcion;
    private double importeInfraccion;

    public InfraccionRequestDTO() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getImporteInfraccion() {
        return importeInfraccion;
    }

    public void setImporteInfraccion(double importeInfraccion) {
        this.importeInfraccion = importeInfraccion;
    }

    public Infraccion toEntity() {
        return new Infraccion(this.descripcion, this.importeInfraccion);
    }
}
