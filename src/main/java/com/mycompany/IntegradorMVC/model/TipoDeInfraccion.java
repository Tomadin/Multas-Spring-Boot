package com.mycompany.IntegradorMVC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tipodeinfraccion")
public class TipoDeInfraccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_infrac")
    private int id_infrac;
    private String descripcionInfraccion;
    private String tipoGravedad;
    private double importeAsignadoInfraccion;
    private double porcentajeDescuento;

    public TipoDeInfraccion() {
    }

    public TipoDeInfraccion(int id_infrac, String descripcionInfraccion, String tipoGravedad,
                             double importeAsignadoInfraccion, double porcentajeDescuento) {
        this.id_infrac = id_infrac;
        this.descripcionInfraccion = descripcionInfraccion;
        this.tipoGravedad = tipoGravedad;
        this.importeAsignadoInfraccion = importeAsignadoInfraccion;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getId_infrac() {
        return id_infrac;
    }

    public void setId_infrac(int id_infrac) {
        this.id_infrac = id_infrac;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public String getTipoGravedad() {
        return tipoGravedad;
    }

    public void setTipoGravedad(String tipoGravedad) {
        this.tipoGravedad = tipoGravedad;
    }

    public double getImporteAsignadoInfraccion() {
        return importeAsignadoInfraccion;
    }

    public void setImporteAsignadoInfraccion(double importeAsignadoInfraccion) {
        this.importeAsignadoInfraccion = importeAsignadoInfraccion;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String toString() {
        return "TipoDeInfraccion{id_infrac=" + id_infrac + ", descripcionInfraccion=" + descripcionInfraccion
                + ", tipoGravedad=" + tipoGravedad + ", importeAsignadoInfraccion=" + importeAsignadoInfraccion
                + ", porcentajeDescuento=" + porcentajeDescuento + '}';
    }
}
