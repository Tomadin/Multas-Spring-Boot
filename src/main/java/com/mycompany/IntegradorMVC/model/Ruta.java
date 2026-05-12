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
@Table(name = "rutas")
public class Ruta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreRuta;
    private String kmRuta;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "tipo_ruta_id")
    private TipoRuta tipoRuta;

    public Ruta() {
    }

    public Ruta(String nombreRuta, String kmRuta, TipoRuta tipoRuta) {
        this.nombreRuta = nombreRuta;
        this.kmRuta = kmRuta;
        this.tipoRuta = tipoRuta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getKmRuta() {
        return kmRuta;
    }

    public void setKmRuta(String kmRuta) {
        this.kmRuta = kmRuta;
    }

    public TipoRuta getTipoRuta() {
        return tipoRuta;
    }

    public void setTipoRuta(TipoRuta tipoRuta) {
        this.tipoRuta = tipoRuta;
    }

    @Override
    public String toString() {
        return "Ruta{nombreRuta=" + nombreRuta + ", kmRuta=" + kmRuta + ", tipoRuta=" + tipoRuta + '}';
    }
}
