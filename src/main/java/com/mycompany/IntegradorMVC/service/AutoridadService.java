package com.mycompany.IntegradorMVC.service;

import com.mycompany.IntegradorMVC.model.AutoridadDeConstatacion;
import java.util.List;

public interface AutoridadService {

    void crearAutoridad(AutoridadDeConstatacion autoridad) throws Exception;

    List<AutoridadDeConstatacion> obtenerTodasLasAutoridades();

    void eliminarAutoridad(int dni) throws Exception;
}
