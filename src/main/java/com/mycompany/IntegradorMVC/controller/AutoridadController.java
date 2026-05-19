package com.mycompany.IntegradorMVC.controller;

import com.mycompany.IntegradorMVC.dto.request.AutoridadRequestDTO;
import com.mycompany.IntegradorMVC.dto.response.AutoridadResponseDTO;
import com.mycompany.IntegradorMVC.service.AutoridadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autoridades")
public class AutoridadController {

    @Autowired
    private AutoridadService autoridadService;

    @GetMapping
    public ResponseEntity<List<AutoridadResponseDTO>> listarAutoridades() {
        List<AutoridadResponseDTO> response = autoridadService.listar().stream()
            .map(AutoridadResponseDTO::from)
            .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> crearAutoridad(@RequestBody AutoridadRequestDTO dto) {
        try {
            autoridadService.crear(dto.toEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body("Autoridad creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAutoridad(@PathVariable Long id) {
        try {
            autoridadService.eliminar(id);
            return ResponseEntity.ok("Autoridad eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
