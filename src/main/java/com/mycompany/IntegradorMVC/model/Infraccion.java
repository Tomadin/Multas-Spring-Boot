package com.mycompany.IntegradorMVC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "infraccion")
public class Infraccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JsonProperty("descripcion")
    private String descripcion;
    private double importeInfraccion;
    @ManyToMany
    @JoinTable(
        name = "infraccion_tipoinfraccion",
        joinColumns = @JoinColumn(name = "infraccion_id"),
        inverseJoinColumns = @JoinColumn(name = "tipo_infraccion_id")
    )
    private List<TipoDeInfraccion> infraccionNomenclanda = new ArrayList<>();

    public Infraccion() {
    }

    public Infraccion(String descripcionInfraccion, double importeInfraccion) {
        this.descripcion = descripcionInfraccion;
        this.importeInfraccion = importeInfraccion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcionInfraccion() {
        return descripcion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcion = descripcionInfraccion;
    }

    public double getImporteInfraccion() {
        return importeInfraccion;
    }

    public void setImporteInfraccion(double importeInfraccion) {
        this.importeInfraccion = importeInfraccion;
    }

    public List<TipoDeInfraccion> getInfraccionNomenclanda() {
        return infraccionNomenclanda;
    }

    public void setInfraccionNomenclanda(List<TipoDeInfraccion> infraccionNomenclanda) {
        this.infraccionNomenclanda = infraccionNomenclanda;
    }

    public void addInfraccionNomenclada(TipoDeInfraccion tipoDeInfraccion) {
        this.infraccionNomenclanda.add(tipoDeInfraccion);
    }

    public List<TipoDeInfraccion> getTipoDeInfraccion() {
        return infraccionNomenclanda;
    }

    @Override
    public String toString() {
        return descripcion + " - $" + importeInfraccion;
    }
}
