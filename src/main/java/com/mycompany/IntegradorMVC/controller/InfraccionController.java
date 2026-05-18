package com.mycompany.IntegradorMVC.controller;

import com.mycompany.IntegradorMVC.model.Infraccion;
import com.mycompany.IntegradorMVC.service.InfraccionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/infracciones")
public class InfraccionController {

    @Autowired
    private InfraccionService infraccionService;

    @GetMapping
    public ResponseEntity<List<Infraccion>> listarInfracciones() {
        return ResponseEntity.ok(infraccionService.obtenerTodasLasInfracciones());
    }

    @PostMapping
    public ResponseEntity<?> crearInfraccion(@RequestBody Infraccion infraccion) {
        try {
            infraccionService.crearInfraccion(infraccion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Infracción creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/importe")
    public ResponseEntity<?> actualizarImporte(@PathVariable int id, @RequestBody Map<String, Double> body) {
        try {
            Double nuevoImporte = body.get("importeInfraccion");
            if (nuevoImporte == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falta el campo importeInfraccion");
            }
            infraccionService.actualizarImporte(id, nuevoImporte);
            return ResponseEntity.ok("Importe actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInfraccion(@PathVariable int id) {
        try {
            infraccionService.eliminarInfraccion(id);
            return ResponseEntity.ok("Infracción eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
