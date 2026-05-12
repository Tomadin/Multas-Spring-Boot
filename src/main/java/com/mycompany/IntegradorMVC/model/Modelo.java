package com.mycompany.IntegradorMVC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "modelos")
public class Modelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String modeloAuto;

    public Modelo() {
    }

    public Modelo(String modeloAuto) {
        this.modeloAuto = modeloAuto;
    }

    public int getId() {
        return id;
    }

    public String getModeloAuto() {
        return modeloAuto;
    }

    public void setModeloAuto(String modeloAuto) {
        this.modeloAuto = modeloAuto;
    }
}
