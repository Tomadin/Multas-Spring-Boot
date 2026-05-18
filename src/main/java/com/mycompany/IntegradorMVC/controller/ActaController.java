package com.mycompany.IntegradorMVC.controller;

import com.mycompany.IntegradorMVC.model.ActaDeConstatacion;
import com.mycompany.IntegradorMVC.service.ActaService;
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

    @GetMapping
    public ResponseEntity<List<ActaDeConstatacion>> listarActas() {
        return ResponseEntity.ok(actaService.listar());
    }

    @PostMapping
    public ResponseEntity<?> crearActa(@RequestBody ActaDeConstatacion acta) {
        try {
            actaService.crear(acta);
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
