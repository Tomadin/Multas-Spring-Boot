package com.mycompany.IntegradorMVC.dto.response;

import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;

public class AutoridadResponseDTO {

    private Long dni;
    private String nombre;
    private String apellido;
    private String genero;
    private int idPlaca;
    private int idLegajo;

    public AutoridadResponseDTO() {
    }

    public static AutoridadResponseDTO from(AutoridadDeConstatacion autoridad) {
        AutoridadResponseDTO dto = new AutoridadResponseDTO();
        dto.dni = autoridad.getDni();
        dto.nombre = autoridad.getNombre();
        dto.apellido = autoridad.getApellido();
        dto.genero = autoridad.getGenero();
        dto.idPlaca = autoridad.getIdPlaca();
        dto.idLegajo = autoridad.getIdLegajo();
        return dto;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
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
}
