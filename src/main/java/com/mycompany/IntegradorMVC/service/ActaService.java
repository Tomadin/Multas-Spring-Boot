package com.mycompany.IntegradorMVC.service;

import com.mycompany.IntegradorMVC.model.ActaDeConstatacion;

public interface ActaService extends CrudService<ActaDeConstatacion, Long> {

    double calcularTotalInfraccionesActa(ActaDeConstatacion acta);

    void cambiarEstado(Long id, String nuevoEstado) throws Exception;
}
