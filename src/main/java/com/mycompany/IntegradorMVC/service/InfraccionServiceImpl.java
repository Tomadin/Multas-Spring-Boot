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
    public void crearInfraccion(Infraccion infraccion) throws Exception {
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
    public List<Infraccion> obtenerTodasLasInfracciones() {
        return infraccionRepository.findAll();
    }

    @Override
    public void eliminarInfraccion(int id) throws Exception {
        if (!infraccionRepository.existsById(id)) {
            throw new Exception("No existe una infracción con id " + id);
        }
        if (actaRepository.existsByInfracciones_Id(id)) {
            throw new Exception("No se puede eliminar: la infracción está asociada a una o más actas");
        }
        infraccionRepository.deleteById(id);
    }
}
