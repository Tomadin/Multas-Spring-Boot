package com.mycompany.IntegradorMVC.controller;

import com.mycompany.IntegradorMVC.dto.request.ActaRequestDTO;
import com.mycompany.IntegradorMVC.dto.response.ActaResponseDTO;
import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;
import com.mycompany.IntegradorMVC.model.Conductor;
import com.mycompany.IntegradorMVC.repository.ConductorRepository;
import com.mycompany.IntegradorMVC.service.ActaService;
import com.mycompany.IntegradorMVC.service.AutoridadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actas")
public class ActaController {

    @Autowired
    private ActaService actaService;

    @Autowired
    private AutoridadService autoridadService;

    @Autowired
    private ConductorRepository conductorRepository;

    @GetMapping
    public ResponseEntity<List<ActaResponseDTO>> listarActas() {
        List<ActaResponseDTO> response = actaService.listar().stream()
            .map(ActaResponseDTO::from)
            .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> crearActa(@RequestBody ActaRequestDTO dto) {
        try {
            AutoridadDeConstatacion autoridad = autoridadService.listarPorId(dto.getAutoridadId());
            ActaRequestDTO.LicenciaData lic = dto.getLicencia();
            Conductor conductor = conductorRepository.findByDni(lic.getConductorDni().intValue())
                .orElseGet(() -> conductorRepository.save(new Conductor(
                    lic.getConductorDomicilio(),
                    lic.getConductorNombre(),
                    lic.getConductorApellido(),
                    lic.getConductorDni(),
                    lic.getConductorGenero()
                )));
            actaService.crear(dto.toEntity(autoridad, conductor));
            return ResponseEntity.status(HttpStatus.CREATED).body("Acta creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        try {
            actaService.cambiarEstado(id, estado);
            return ResponseEntity.ok("Estado actualizado a " + estado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
