package com.mycompany.IntegradorMVC.service;

import com.mycompany.IntegradorMVC.model.Infraccion;
import com.mycompany.IntegradorMVC.repository.ActaDeConstatacionRepository;
import com.mycompany.IntegradorMVC.repository.InfraccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfraccionServiceImpl implements InfraccionService {

    @Autowired
    private InfraccionRepository infraccionRepository;
    @Autowired
    private ActaDeConstatacionRepository actaRepository;

    @Override
    public List<Infraccion> listar() {
        return infraccionRepository.findAll();
    }

    @Override
    public Infraccion listarPorId(Long id) throws Exception {
        return infraccionRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe una infracción con id " + id));
    }

    @Override
    public void crear(Infraccion infraccion) throws Exception {
        if (infraccion == null) {
            throw new Exception("La infracción no puede ser nula");
        }
        if (infraccion.getImporteInfraccion() <= 0) {
            throw new Exception("El importe debe ser mayor a 0");
        }
        if (infraccion.getDescripcionInfraccion() == null
                || infraccion.getDescripcionInfraccion().trim().isEmpty()) {
            throw new Exception("La descripción es obligatoria");
        }
        if (infraccion.getImporteInfraccion() > 50000) {
            System.out.println("Infracción de alto importe - Requiere autorización especial");
        }
        infraccionRepository.save(infraccion);
    }

    @Override
    public void actualizar(Infraccion infraccion) throws Exception {
        if (infraccion == null || infraccion.getId() == null) {
            throw new Exception("Infracción inválida para actualizar");
        }
        infraccionRepository.save(infraccion);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        if (!infraccionRepository.existsById(id)) {
            throw new Exception("No existe una infracción con id " + id);
        }
        if (actaRepository.existsByInfracciones_Id(id)) {
            throw new Exception("No se puede eliminar: la infracción está asociada a una o más actas");
        }
        infraccionRepository.deleteById(id);
    }

    @Override
    public void actualizarImporte(Long id, double nuevoImporte) throws Exception {
        Infraccion infraccion = infraccionRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe una infracción con id " + id));
        if (nuevoImporte <= 0) {
            throw new Exception("El importe debe ser mayor a 0");
        }
        infraccion.setImporteInfraccion(nuevoImporte);
        infraccionRepository.save(infraccion);
    }
}
