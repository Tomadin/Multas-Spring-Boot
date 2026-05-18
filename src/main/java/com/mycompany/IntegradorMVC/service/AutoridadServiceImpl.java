package com.mycompany.IntegradorMVC.service;

import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;
import com.mycompany.IntegradorMVC.repository.ActaDeConstatacionRepository;
import com.mycompany.IntegradorMVC.repository.AutoridadDeConstatacionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoridadServiceImpl implements AutoridadService {

    @Autowired
    private AutoridadDeConstatacionRepository autoridadRepository;
    @Autowired
    private ActaDeConstatacionRepository actaRepository;

    @Override
    public List<AutoridadDeConstatacion> listar() {
        return autoridadRepository.findAll();
    }

    @Override
    public AutoridadDeConstatacion listarPorId(Long id) throws Exception {
        return autoridadRepository.findById(id)
                .orElseThrow(() -> new Exception("Autoridad no encontrada con id: " + id));
    }

    @Override
    public void crear(AutoridadDeConstatacion autoridad) throws Exception {
        if (autoridad == null) {
            throw new Exception("La autoridad no puede ser nula");
        }
        if (autoridad.getDni() == null || autoridad.getDni() <= 0) {
            throw new Exception("DNI inválido");
        }
        if (autoridad.getNombre() == null || autoridad.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }
        if (autoridadRepository.existsByDni(autoridad.getDni())) {
            throw new Exception("Ya existe una autoridad con DNI " + autoridad.getDni());
        }
        autoridadRepository.save(autoridad);
    }

    @Override
    public void actualizar(AutoridadDeConstatacion autoridad) throws Exception {
        if (autoridad == null || autoridad.getDni() == null) {
            throw new Exception("Autoridad inválida para actualizar");
        }
        autoridadRepository.save(autoridad);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        if (!autoridadRepository.existsById(id)) {
            throw new Exception("No existe una autoridad con id " + id);
        }
        if (actaRepository.existsByAutoridadDeConstatacion_Dni(id)) {
            throw new Exception("No se puede eliminar: la autoridad tiene actas asociadas");
        }
        autoridadRepository.deleteById(id);
    }
}
