package com.mycompany.IntegradorMVC.service;

import com.mycompany.IntegradorMVC.model.Infraccion;
import java.util.List;

public interface InfraccionService {

    void crearInfraccion(Infraccion infraccion) throws Exception;

    List<Infraccion> obtenerTodasLasInfracciones();

    void eliminarInfraccion(int id) throws Exception;

    void actualizarImporte(int id, double nuevoImporte) throws Exception;
}
