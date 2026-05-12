package com.mycompany.IntegradorMVC;

import com.mycompany.IntegradorMVC.model.ActaDeConstatacion;
import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;
import com.mycompany.IntegradorMVC.model.Conductor;
import com.mycompany.IntegradorMVC.model.EstadoDelActa;
import com.mycompany.IntegradorMVC.model.Infraccion;
import com.mycompany.IntegradorMVC.model.Licencia;
import com.mycompany.IntegradorMVC.model.Marca;
import com.mycompany.IntegradorMVC.model.Modelo;
import com.mycompany.IntegradorMVC.model.OrganizacionEstatal;
import com.mycompany.IntegradorMVC.model.Ruta;
import com.mycompany.IntegradorMVC.model.TipoRuta;
import com.mycompany.IntegradorMVC.model.Vehiculo;
import com.mycompany.IntegradorMVC.repository.ActaDeConstatacionRepository;
import com.mycompany.IntegradorMVC.repository.AutoridadDeConstatacionRepository;
import com.mycompany.IntegradorMVC.repository.InfraccionRepository;
import com.mycompany.IntegradorMVC.service.ActaService;
import jakarta.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired private InfraccionRepository infraccionRepository;
    @Autowired private AutoridadDeConstatacionRepository autoridadRepository;
    @Autowired private ActaDeConstatacionRepository actaRepository;
    @Autowired private ActaService actaService;

    @PostConstruct
    public void init() {
        seedInfracciones();
        seedAutoridades();
        seedActas();
    }

    private void seedInfracciones() {
        if (infraccionRepository.count() > 0) return;
        infraccionRepository.saveAll(List.of(
            new Infraccion("Exceso de velocidad en zona urbana", 15000),
            new Infraccion("Exceso de velocidad en ruta o autopista", 25000),
            new Infraccion("Conducir bajo efectos del alcohol (0,2–0,5 g/l)", 30000),
            new Infraccion("Conducir bajo efectos del alcohol (mayor a 0,5 g/l)", 60000),
            new Infraccion("No respetar semáforo en rojo", 12000),
            new Infraccion("Uso de celular al volante", 18000),
            new Infraccion("No usar cinturón de seguridad", 10000),
            new Infraccion("Estacionamiento en lugar prohibido", 8000),
            new Infraccion("Conducir sin licencia habilitante", 45000),
            new Infraccion("No respetar señal de STOP", 11000),
            new Infraccion("Giro prohibido", 9000),
            new Infraccion("Documentación del vehículo vencida o faltante", 7000),
            new Infraccion("Conducir en sentido contrario", 20000),
            new Infraccion("No ceder el paso a peatones en senda", 13000),
            new Infraccion("Vehículo sin seguro obligatorio vigente", 35000)
        ));
    }

    private void seedAutoridades() {
        if (autoridadRepository.count() > 0) return;
        autoridadRepository.saveAll(List.of(
            new AutoridadDeConstatacion(1001, 2001, "Juan",    "García",    25000001, "M"),
            new AutoridadDeConstatacion(1002, 2002, "María",   "López",     27000002, "F"),
            new AutoridadDeConstatacion(1003, 2003, "Carlos",  "Rodríguez", 29000003, "M"),
            new AutoridadDeConstatacion(1004, 2004, "Ana",     "Martínez",  31000004, "F"),
            new AutoridadDeConstatacion(1005, 2005, "Pablo",   "Sánchez",   33000005, "M"),
            new AutoridadDeConstatacion(1006, 2006, "Laura",   "Fernández", 35000006, "F")
        ));
    }

    private void seedActas() {
        if (actaRepository.count() > 0) return;

        List<Infraccion> infs = infraccionRepository.findAll();
        List<AutoridadDeConstatacion> auts = autoridadRepository.findAll();
        if (infs.size() < 12 || auts.size() < 3) return;

        try {
            // ── Acta 1: Exceso de velocidad (PENDIENTE) ──────────────────────────
            ActaDeConstatacion acta1 = new ActaDeConstatacion(
                Date.valueOf("2025-03-10"),
                Date.valueOf("2025-04-10"),
                LocalDateTime.of(2025, 3, 10, 14, 30),
                "Av. San Martín 1200, Mendoza",
                "Conductor reincidente en exceso de velocidad",
                new OrganizacionEstatal("Policía de Mendoza", "Mendoza"),
                new Vehiculo("Rojo", "MZA001AB", 2019, new Marca("Ford", new Modelo("Focus"))),
                new EstadoDelActa("Acta generada", "PENDIENTE"),
                auts.get(0),
                null,
                new Ruta("Ruta Nacional 40", "350", new TipoRuta("Ruta nacional asfaltada", "Nacional"))
            );
            Licencia lic1 = new Licencia(100001, Date.valueOf("2027-06-30"), 20);
            lic1.setConductor(new Conductor("Belgrano 450, Mendoza", "Roberto", "Pérez", 40000001, "M"));
            acta1.setLicencia(lic1);
            acta1.setInfracciones(new ArrayList<>(List.of(infs.get(0), infs.get(1))));
            actaService.crearActa(acta1);

            // ── Acta 2: Control de alcoholemia (PAGADO) ──────────────────────────
            ActaDeConstatacion acta2 = new ActaDeConstatacion(
                Date.valueOf("2025-04-05"),
                Date.valueOf("2025-05-05"),
                LocalDateTime.of(2025, 4, 5, 22, 15),
                "Rotonda Costanera, Godoy Cruz",
                "Control nocturno de alcoholemia",
                new OrganizacionEstatal("Municipalidad de Godoy Cruz", "Mendoza"),
                new Vehiculo("Blanco", "GDC002XY", 2021, new Marca("Chevrolet", new Modelo("Cruze"))),
                new EstadoDelActa("Acta pagada", "PAGADO"),
                auts.get(1),
                null,
                new Ruta("Acceso Este", "12", new TipoRuta("Acceso controlado", "Provincial"))
            );
            Licencia lic2 = new Licencia(200002, Date.valueOf("2026-11-15"), 20);
            lic2.setConductor(new Conductor("Las Heras 890, Godoy Cruz", "Valeria", "Torres", 42000002, "F"));
            acta2.setLicencia(lic2);
            acta2.setInfracciones(new ArrayList<>(List.of(infs.get(2), infs.get(5))));
            actaService.crearActa(acta2);

            // ── Acta 3: Sin documentación (PENDIENTE) ───────────────────────────
            ActaDeConstatacion acta3 = new ActaDeConstatacion(
                Date.valueOf("2025-05-20"),
                Date.valueOf("2025-06-20"),
                LocalDateTime.of(2025, 5, 20, 9, 0),
                "Ruta Nacional 40 km 1150, Las Heras",
                "Vehículo sin documentación en regla",
                new OrganizacionEstatal("Gendarmería Nacional Argentina", "Nacional"),
                new Vehiculo("Gris", "LH003PQ", 2016, new Marca("Volkswagen", new Modelo("Gol"))),
                new EstadoDelActa("Acta generada", "PENDIENTE"),
                auts.get(2),
                null,
                new Ruta("Ruta Nacional 40", "1150", new TipoRuta("Ruta nacional asfaltada", "Nacional"))
            );
            Licencia lic3 = new Licencia(300003, Date.valueOf("2025-02-28"), 20);
            lic3.setConductor(new Conductor("Mitre 200, Las Heras", "Diego", "Morales", 38000003, "M"));
            acta3.setLicencia(lic3);
            acta3.setInfracciones(new ArrayList<>(List.of(infs.get(8), infs.get(11))));
            actaService.crearActa(acta3);

        } catch (Exception e) {
            System.err.println("[DataInitializer] Error al crear actas de ejemplo: " + e.getMessage());
        }
    }
}
