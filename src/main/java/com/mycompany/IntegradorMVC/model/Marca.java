package com.mycompany.IntegradorMVC.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "marcas")
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marcaAuto;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    public Marca() {
    }

    public Marca(String marcaAuto, Modelo modelo) {
        this.marcaAuto = marcaAuto;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public String getMarcaAuto() {
        return marcaAuto;
    }

    public void setMarcaAuto(String marcaAuto) {
        this.marcaAuto = marcaAuto;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
