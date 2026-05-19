package com.mycompany.IntegradorMVC.dto.response;

import com.mycompany.IntegradorMVC.model.Infraccion;

public class InfraccionResponseDTO {

    private Long id;
    private String descripcion;
    private double importeInfraccion;

    public InfraccionResponseDTO() {
    }

    public static InfraccionResponseDTO from(Infraccion infraccion) {
        InfraccionResponseDTO dto = new InfraccionResponseDTO();
        dto.id = infraccion.getId();
        dto.descripcion = infraccion.getDescripcionInfraccion();
        dto.importeInfraccion = infraccion.getImporteInfraccion();
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
