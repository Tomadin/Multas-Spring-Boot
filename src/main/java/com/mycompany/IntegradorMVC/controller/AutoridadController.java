package com.mycompany.IntegradorMVC.controller;

import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;
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
    public ResponseEntity<List<AutoridadDeConstatacion>> listarAutoridades() {
        return ResponseEntity.ok(autoridadService.obtenerTodasLasAutoridades());
    }

    @PostMapping
    public ResponseEntity<?> crearAutoridad(@RequestBody AutoridadDeConstatacion autoridad) {
        try {
            autoridadService.crearAutoridad(autoridad);
            return ResponseEntity.status(HttpStatus.CREATED).body("Autoridad creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<?> eliminarAutoridad(@PathVariable int dni) {
        try {
            autoridadService.eliminarAutoridad(dni);
            return ResponseEntity.ok("Autoridad eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
