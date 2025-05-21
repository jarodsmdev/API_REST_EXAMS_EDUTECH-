package com.briones.grades.service;

import java.util.List;

public interface ICrudService<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T object);
    void delete(Long id);
}
