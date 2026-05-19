package com.mycompany.IntegradorMVC.dto.request;

import com.mycompany.IntegradorMVC.model.ActaDeConstatacion;
import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;
import com.mycompany.IntegradorMVC.model.Conductor;
import com.mycompany.IntegradorMVC.model.EstadoDelActa;
import com.mycompany.IntegradorMVC.model.Licencia;
import com.mycompany.IntegradorMVC.model.Marca;
import com.mycompany.IntegradorMVC.model.Modelo;
import com.mycompany.IntegradorMVC.model.OrganizacionEstatal;
import com.mycompany.IntegradorMVC.model.Ruta;
import com.mycompany.IntegradorMVC.model.TipoRuta;
import com.mycompany.IntegradorMVC.model.Vehiculo;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ActaRequestDTO {

    private Date fechaDeLabrado;
    private Date fechaVtoPagoVolun;
    private LocalDateTime horaDeLabrado;
    private String lugarDeConstatacion;
    private String observaciones;
    private Long autoridadId;
    private List<InfraccionRequestDTO> infracciones;
    private VehiculoData vehiculo;
    private EstadoData estadoDelActa;
    private OrganizacionData organizacionEstatal;
    private RutaData ruta;
    private LicenciaData licencia;

    public ActaRequestDTO() {
    }

    public ActaDeConstatacion toEntity(AutoridadDeConstatacion autoridad, Conductor conductor) {
        ActaDeConstatacion acta = new ActaDeConstatacion();
        acta.setFechaDeLabrado(this.fechaDeLabrado);
        acta.setFechaVtoPagoVolun(this.fechaVtoPagoVolun);
        acta.setHoraDeLabrado(this.horaDeLabrado);
        acta.setLugarDeConstatacion(this.lugarDeConstatacion);
        acta.setObservaciones(this.observaciones);
        acta.setAutoridadDeConstatacion(autoridad);

        if (this.infracciones != null) {
            this.infracciones.forEach(i -> acta.addInfraccion(i.toEntity()));
        }

        if (this.vehiculo != null) {
            Modelo modelo = new Modelo(this.vehiculo.getModeloNombre());
            Marca marca = new Marca(this.vehiculo.getMarcaNombre(), modelo);
            Vehiculo v = new Vehiculo(
                this.vehiculo.getColor(),
                this.vehiculo.getDominio(),
                this.vehiculo.getAnioPatentamiento(),
                marca
            );
            acta.setVehiculo(v);
        }

        if (this.estadoDelActa != null) {
            acta.setEstadoDelActa(new EstadoDelActa(
                this.estadoDelActa.getDescripcion(),
                this.estadoDelActa.getNombre()
            ));
        }

        if (this.organizacionEstatal != null) {
            acta.setOrganizacionEstatal(new OrganizacionEstatal(
                this.organizacionEstatal.getNombreOrganizacion(),
                this.organizacionEstatal.getLocalidad()
            ));
        }

        if (this.ruta != null) {
            TipoRuta tipoRuta = new TipoRuta(
                this.ruta.getDescTipoRuta(),
                this.ruta.getNombreTipoRuta()
            );
            acta.setRuta(new Ruta(this.ruta.getNombreRuta(), this.ruta.getKmRuta(), tipoRuta));
        }

        if (this.licencia != null) {
            Licencia lic = new Licencia(
                this.licencia.getNumeroLicencia(),
                this.licencia.getFechaDeVto(),
                this.licencia.getPuntosInicialesLicencia()
            );
            lic.setConductor(conductor);
            acta.setLicencia(lic);
        }

        return acta;
    }

    // --- Getters y setters ---

    public Date getFechaDeLabrado() { return fechaDeLabrado; }
    public void setFechaDeLabrado(Date fechaDeLabrado) { this.fechaDeLabrado = fechaDeLabrado; }

    public Date getFechaVtoPagoVolun() { return fechaVtoPagoVolun; }
    public void setFechaVtoPagoVolun(Date fechaVtoPagoVolun) { this.fechaVtoPagoVolun = fechaVtoPagoVolun; }

    public LocalDateTime getHoraDeLabrado() { return horaDeLabrado; }
    public void setHoraDeLabrado(LocalDateTime horaDeLabrado) { this.horaDeLabrado = horaDeLabrado; }

    public String getLugarDeConstatacion() { return lugarDeConstatacion; }
    public void setLugarDeConstatacion(String lugarDeConstatacion) { this.lugarDeConstatacion = lugarDeConstatacion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Long getAutoridadId() { return autoridadId; }
    public void setAutoridadId(Long autoridadId) { this.autoridadId = autoridadId; }

    public List<InfraccionRequestDTO> getInfracciones() { return infracciones; }
    public void setInfracciones(List<InfraccionRequestDTO> infracciones) { this.infracciones = infracciones; }

    public VehiculoData getVehiculo() { return vehiculo; }
    public void setVehiculo(VehiculoData vehiculo) { this.vehiculo = vehiculo; }

    public EstadoData getEstadoDelActa() { return estadoDelActa; }
    public void setEstadoDelActa(EstadoData estadoDelActa) { this.estadoDelActa = estadoDelActa; }

    public OrganizacionData getOrganizacionEstatal() { return organizacionEstatal; }
    public void setOrganizacionEstatal(OrganizacionData organizacionEstatal) { this.organizacionEstatal = organizacionEstatal; }

    public RutaData getRuta() { return ruta; }
    public void setRuta(RutaData ruta) { this.ruta = ruta; }

    public LicenciaData getLicencia() { return licencia; }
    public void setLicencia(LicenciaData licencia) { this.licencia = licencia; }

    // --- Clases internas para entidades con cascada ---

    public static class VehiculoData {
        private String color;
        private String dominio;
        private int anioPatentamiento;
        private String marcaNombre;
        private String modeloNombre;

        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }

        public String getDominio() { return dominio; }
        public void setDominio(String dominio) { this.dominio = dominio; }

        public int getAnioPatentamiento() { return anioPatentamiento; }
        public void setAnioPatentamiento(int anioPatentamiento) { this.anioPatentamiento = anioPatentamiento; }

        public String getMarcaNombre() { return marcaNombre; }
        public void setMarcaNombre(String marcaNombre) { this.marcaNombre = marcaNombre; }

        public String getModeloNombre() { return modeloNombre; }
        public void setModeloNombre(String modeloNombre) { this.modeloNombre = modeloNombre; }
    }

    public static class EstadoData {
        private String descripcion;
        private String nombre;

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
    }

    public static class OrganizacionData {
        private String nombreOrganizacion;
        private String localidad;

        public String getNombreOrganizacion() { return nombreOrganizacion; }
        public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }

        public String getLocalidad() { return localidad; }
        public void setLocalidad(String localidad) { this.localidad = localidad; }
    }

    public static class RutaData {
        private String nombreRuta;
        private String kmRuta;
        private String nombreTipoRuta;
        private String descTipoRuta;

        public String getNombreRuta() { return nombreRuta; }
        public void setNombreRuta(String nombreRuta) { this.nombreRuta = nombreRuta; }

        public String getKmRuta() { return kmRuta; }
        public void setKmRuta(String kmRuta) { this.kmRuta = kmRuta; }

        public String getNombreTipoRuta() { return nombreTipoRuta; }
        public void setNombreTipoRuta(String nombreTipoRuta) { this.nombreTipoRuta = nombreTipoRuta; }

        public String getDescTipoRuta() { return descTipoRuta; }
        public void setDescTipoRuta(String descTipoRuta) { this.descTipoRuta = descTipoRuta; }
    }

    public static class LicenciaData {
        private int numeroLicencia;
        private Date fechaDeVto;
        private int puntosInicialesLicencia;
        private String conductorNombre;
        private String conductorApellido;
        private Long conductorDni;
        private String conductorGenero;
        private String conductorDomicilio;

        public int getNumeroLicencia() { return numeroLicencia; }
        public void setNumeroLicencia(int numeroLicencia) { this.numeroLicencia = numeroLicencia; }

        public Date getFechaDeVto() { return fechaDeVto; }
        public void setFechaDeVto(Date fechaDeVto) { this.fechaDeVto = fechaDeVto; }

        public int getPuntosInicialesLicencia() { return puntosInicialesLicencia; }
        public void setPuntosInicialesLicencia(int puntosInicialesLicencia) { this.puntosInicialesLicencia = puntosInicialesLicencia; }

        public String getConductorNombre() { return conductorNombre; }
        public void setConductorNombre(String conductorNombre) { this.conductorNombre = conductorNombre; }

        public String getConductorApellido() { return conductorApellido; }
        public void setConductorApellido(String conductorApellido) { this.conductorApellido = conductorApellido; }

        public Long getConductorDni() { return conductorDni; }
        public void setConductorDni(Long conductorDni) { this.conductorDni = conductorDni; }

        public String getConductorGenero() { return conductorGenero; }
        public void setConductorGenero(String conductorGenero) { this.conductorGenero = conductorGenero; }

        public String getConductorDomicilio() { return conductorDomicilio; }
        public void setConductorDomicilio(String conductorDomicilio) { this.conductorDomicilio = conductorDomicilio; }
    }
}
