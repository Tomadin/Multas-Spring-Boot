package com.mycompany.IntegradorMVC.service;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> listar();

    T listarPorId(ID id) throws Exception;

    void crear(T entity) throws Exception;

    void actualizar(T entity) throws Exception;

    void eliminar(ID id) throws Exception;
}
