package com.mycompany.IntegradorMVC.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("CONDUCTOR")
public class Conductor extends Persona implements Serializable {

    private String domicilio;

    public Conductor() {
    }

    public Conductor(String domicilio, String nombre, String apellido, Long dni, String genero) {
        super(nombre, apellido, dni, genero);
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Conductor{domicilio=" + domicilio + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + '}';
    }
}
