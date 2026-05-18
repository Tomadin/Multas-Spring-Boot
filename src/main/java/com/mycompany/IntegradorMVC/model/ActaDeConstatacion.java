package com.mycompany.IntegradorMVC.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "actas")
public class ActaDeConstatacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActa;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "acta_id")
    private List<Infraccion> infracciones = new ArrayList<>();
    private Date fechaDeLabrado;
    private Date fechaVtoPagoVolun;
    private LocalDateTime horaDeLabrado;
    private String lugarDeConstatacion;
    private String observaciones;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organizacion_id")
    private OrganizacionEstatal organizacionEstatal;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "estado_id")
    private EstadoDelActa estadoDelActa;
    @ManyToOne
    @JoinColumn(name = "autoridad_id")
    private AutoridadDeConstatacion autoridadDeConstatacion;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "licencia_id")
    private Licencia licencia;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;

    public ActaDeConstatacion() {
    }

    public ActaDeConstatacion(Date fechaDeLabrado, Date fechaVtoPagoVolun, LocalDateTime horaDeLabrado,
                               String lugarDeConstatacion, String observaciones,
                               OrganizacionEstatal organizacionEstatal, Vehiculo vehiculo,
                               EstadoDelActa estadoDelActa, AutoridadDeConstatacion autoridadDeConstatacion,
                               Licencia licencia, Ruta ruta) {
        this.fechaDeLabrado = fechaDeLabrado;
        this.fechaVtoPagoVolun = fechaVtoPagoVolun;
        this.horaDeLabrado = horaDeLabrado;
        this.lugarDeConstatacion = lugarDeConstatacion;
        this.observaciones = observaciones;
        this.organizacionEstatal = organizacionEstatal;
        this.vehiculo = vehiculo;
        this.estadoDelActa = estadoDelActa;
        this.autoridadDeConstatacion = autoridadDeConstatacion;
        this.licencia = licencia;
        this.ruta = ruta;
    }

    public Long getIdActa() {
        return idActa;
    }

    public void setIdActa(Long idActa) {
        this.idActa = idActa;
    }

    public List<Infraccion> getInfracciones() {
        return infracciones;
    }

    public void setInfracciones(List<Infraccion> infracciones) {
        this.infracciones = infracciones;
    }

    public void addInfraccion(Infraccion infraccion) {
        this.infracciones.add(infraccion);
    }

    public Date getFechaDeLabrado() {
        return fechaDeLabrado;
    }

    public void setFechaDeLabrado(Date fechaDeLabrado) {
        this.fechaDeLabrado = fechaDeLabrado;
    }

    public Date getFechaVtoPagoVolun() {
        return fechaVtoPagoVolun;
    }

    public void setFechaVtoPagoVolun(Date fechaVtoPagoVolun) {
        this.fechaVtoPagoVolun = fechaVtoPagoVolun;
    }

    public LocalDateTime getHoraDeLabrado() {
        return horaDeLabrado;
    }

    public void setHoraDeLabrado(LocalDateTime horaDeLabrado) {
        this.horaDeLabrado = horaDeLabrado;
    }

    public String getLugarDeConstatacion() {
        return lugarDeConstatacion;
    }

    public void setLugarDeConstatacion(String lugarDeConstatacion) {
        this.lugarDeConstatacion = lugarDeConstatacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public OrganizacionEstatal getOrganizacionEstatal() {
        return organizacionEstatal;
    }

    public void setOrganizacionEstatal(OrganizacionEstatal organizacionEstatal) {
        this.organizacionEstatal = organizacionEstatal;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public EstadoDelActa getEstadoDelActa() {
        return estadoDelActa;
    }

    public void setEstadoDelActa(EstadoDelActa estadoDelActa) {
        this.estadoDelActa = estadoDelActa;
    }

    public AutoridadDeConstatacion getAutoridadDeConstatacion() {
        return autoridadDeConstatacion;
    }

    public void setAutoridadDeConstatacion(AutoridadDeConstatacion autoridadDeConstatacion) {
        this.autoridadDeConstatacion = autoridadDeConstatacion;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "ActaDeConstatacion{idActa=" + idActa + ", fechaDeLabrado=" + fechaDeLabrado
                + ", lugarDeConstatacion=" + lugarDeConstatacion + '}';
    }
}
