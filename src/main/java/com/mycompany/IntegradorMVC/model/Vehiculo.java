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
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String color;
    private String dominio;
    private int anioPatentamiento;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Vehiculo() {
    }

    public Vehiculo(String color, String dominio, int anioPatentamiento, Marca marca) {
        this.color = color;
        this.dominio = dominio;
        this.anioPatentamiento = anioPatentamiento;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public int getAnioPatentamiento() {
        return anioPatentamiento;
    }

    public void setAnioPatentamiento(int anioPatentamiento) {
        this.anioPatentamiento = anioPatentamiento;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Vehiculo{color=" + color + ", dominio=" + dominio + ", anioPatentamiento=" + anioPatentamiento + ", marca=" + marca + '}';
    }
}
