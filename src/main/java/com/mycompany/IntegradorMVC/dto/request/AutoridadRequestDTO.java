package com.mycompany.IntegradorMVC.dto.request;

import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;

public class AutoridadRequestDTO {

    private String nombre;
    private String apellido;
    private Long dni;
    private String genero;
    private int idPlaca;
    private int idLegajo;

    public AutoridadRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(int idPlaca) {
        this.idPlaca = idPlaca;
    }

    public int getIdLegajo() {
        return idLegajo;
    }

    public void setIdLegajo(int idLegajo) {
        this.idLegajo = idLegajo;
    }

    public AutoridadDeConstatacion toEntity() {
        return new AutoridadDeConstatacion(
            this.idPlaca,
            this.idLegajo,
            this.nombre,
            this.apellido,
            this.dni,
            this.genero
        );
    }
}
