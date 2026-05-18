package com.mycompany.IntegradorMVC.service;

import com.mycompany.IntegradorMVC.model.Infraccion;

public interface InfraccionService extends CrudService<Infraccion, Long> {

    void actualizarImporte(Long id, double nuevoImporte) throws Exception;
}
