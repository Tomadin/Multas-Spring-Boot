package com.mycompany.IntegradorMVC.service;

import com.mycompany.IntegradorMVC.model.ActaDeConstatacion;
import com.mycompany.IntegradorMVC.model.EstadoDelActa;
import com.mycompany.IntegradorMVC.model.Infraccion;
import com.mycompany.IntegradorMVC.repository.ActaDeConstatacionRepository;
import com.mycompany.IntegradorMVC.repository.AutoridadDeConstatacionRepository;
import com.mycompany.IntegradorMVC.repository.ConductorRepository;
import com.mycompany.IntegradorMVC.repository.InfraccionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActaServiceImpl implements ActaService {

    @Autowired
    private ActaDeConstatacionRepository actaRepository;
    @Autowired
    private AutoridadDeConstatacionRepository autoridadRepository;
    @Autowired
    private ConductorRepository conductorRepository;
    @Autowired
    private InfraccionRepository infraccionRepository;

    @Override
    public List<ActaDeConstatacion> listar() {
        return actaRepository.findAll();
    }

    @Override
    public ActaDeConstatacion listarPorId(Long id) throws Exception {
        return actaRepository.findById(id)
                .orElseThrow(() -> new Exception("Acta no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public void crear(ActaDeConstatacion acta) throws Exception {
        if (acta == null) {
            throw new Exception("El acta no puede ser nula");
        }
        if (acta.getVehiculo() == null) {
            throw new Exception("El acta debe estar asociada a un vehículo");
        }
        if (acta.getLicencia() == null) {
            throw new Exception("El acta debe tener una licencia asociada");
        }
        if (acta.getAutoridadDeConstatacion() == null) {
            throw new Exception("El acta debe tener una autoridad de constatación");
        }

        // Guardar conductor antes de la licencia (tiene dni no-zero)
        if (acta.getLicencia().getConductor() != null) {
            acta.getLicencia().setConductor(
                conductorRepository.save(acta.getLicencia().getConductor())
            );
        }

        // Guardar autoridad antes del acta (tiene dni no-zero)
        acta.setAutoridadDeConstatacion(
            autoridadRepository.save(acta.getAutoridadDeConstatacion())
        );

        // Re-obtener infracciones por ID si ya existen; las nuevas (sin ID) se persisten en cascada
        if (acta.getInfracciones() != null && !acta.getInfracciones().isEmpty()) {
            List<Infraccion> gestionadas = new ArrayList<>();
            for (Infraccion inf : acta.getInfracciones()) {
                if (inf.getId() != null) {
                    infraccionRepository.findById(inf.getId()).ifPresent(gestionadas::add);
                } else {
                    gestionadas.add(inf);
                }
            }
            acta.setInfracciones(gestionadas);
        }

        actaRepository.save(acta);
    }

    @Override
    public void actualizar(ActaDeConstatacion acta) throws Exception {
        if (acta == null || acta.getIdActa() == null) {
            throw new Exception("Acta inválida para actualizar");
        }
        actaRepository.save(acta);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        if (!actaRepository.existsById(id)) {
            throw new Exception("No existe un acta con id: " + id);
        }
        actaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void cambiarEstado(Long id, String nuevoEstado) throws Exception {
        ActaDeConstatacion acta = actaRepository.findById(id)
                .orElseThrow(() -> new Exception("Acta no encontrada con id: " + id));

        if (!nuevoEstado.equals("PAGADO") && !nuevoEstado.equals("CANCELADO")) {
            throw new Exception("Estado inválido. Use PAGADO o CANCELADO");
        }

        EstadoDelActa estado = acta.getEstadoDelActa();
        if (estado == null) {
            throw new Exception("El acta no tiene estado asignado");
        }

        estado.setNombreEstadoActa(nuevoEstado);
        estado.setDescripcionEstadoActa(nuevoEstado.equals("PAGADO") ? "Acta pagada" : "Acta cancelada");
    }

    @Override
    public double calcularTotalInfraccionesActa(ActaDeConstatacion acta) {
        if (acta == null || acta.getInfracciones() == null) {
            return 0.0;
        }
        double total = 0.0;
        for (Infraccion inf : acta.getInfracciones()) {
            total += inf.getImporteInfraccion();
        }
        return total;
    }
}
