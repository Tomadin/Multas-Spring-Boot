package com.mycompany.IntegradorMVC.service;

import com.mycompany.IntegradorMVC.model.ActaDeConstatacion;
import java.util.List;

public interface ActaService {

    void crearActa(ActaDeConstatacion acta) throws Exception;

    List<ActaDeConstatacion> obtenerTodasLasActas();

    double calcularTotalInfraccionesActa(ActaDeConstatacion acta);

    void cambiarEstado(int id, String nuevoEstado) throws Exception;
}
