package com.mycompany.IntegradorMVC.dto.response;

import com.mycompany.IntegradorMVC.model.ActaDeConstatacion;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ActaResponseDTO {

    private Long idActa;
    private Date fechaDeLabrado;
    private Date fechaVtoPagoVolun;
    private LocalDateTime horaDeLabrado;
    private String lugarDeConstatacion;
    private String observaciones;
    private String estado;
    private String autoridad;
    private String conductor;
    private String vehiculoDominio;
    private String organizacion;
    private List<InfraccionResponseDTO> infracciones;

    public ActaResponseDTO() {
    }

    public static ActaResponseDTO from(ActaDeConstatacion acta) {
        ActaResponseDTO dto = new ActaResponseDTO();
        dto.idActa = acta.getIdActa();
        dto.fechaDeLabrado = acta.getFechaDeLabrado();
        dto.fechaVtoPagoVolun = acta.getFechaVtoPagoVolun();
        dto.horaDeLabrado = acta.getHoraDeLabrado();
        dto.lugarDeConstatacion = acta.getLugarDeConstatacion();
        dto.observaciones = acta.getObservaciones();

        if (acta.getEstadoDelActa() != null) {
            dto.estado = acta.getEstadoDelActa().getNombreEstadoActa();
        }
        if (acta.getAutoridadDeConstatacion() != null) {
            dto.autoridad = acta.getAutoridadDeConstatacion().getNombre()
                + " " + acta.getAutoridadDeConstatacion().getApellido();
        }
        if (acta.getLicencia() != null && acta.getLicencia().getConductor() != null) {
            dto.conductor = acta.getLicencia().getConductor().getNombre()
                + " " + acta.getLicencia().getConductor().getApellido();
        }
        if (acta.getVehiculo() != null) {
            dto.vehiculoDominio = acta.getVehiculo().getDominio();
        }
        if (acta.getOrganizacionEstatal() != null) {
            dto.organizacion = acta.getOrganizacionEstatal().getNombreOrganizacion();
        }

        dto.infracciones = acta.getInfracciones().stream()
            .map(InfraccionResponseDTO::from)
            .toList();

        return dto;
    }

    public Long getIdActa() { return idActa; }
    public void setIdActa(Long idActa) { this.idActa = idActa; }

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

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getAutoridad() { return autoridad; }
    public void setAutoridad(String autoridad) { this.autoridad = autoridad; }

    public String getConductor() { return conductor; }
    public void setConductor(String conductor) { this.conductor = conductor; }

    public String getVehiculoDominio() { return vehiculoDominio; }
    public void setVehiculoDominio(String vehiculoDominio) { this.vehiculoDominio = vehiculoDominio; }

    public String getOrganizacion() { return organizacion; }
    public void setOrganizacion(String organizacion) { this.organizacion = organizacion; }

    public List<InfraccionResponseDTO> getInfracciones() { return infracciones; }
    public void setInfracciones(List<InfraccionResponseDTO> infracciones) { this.infracciones = infracciones; }
}
