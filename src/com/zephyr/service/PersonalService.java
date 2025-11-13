package com.zephyr.service;

import com.zephyr.entity.Personal;

import java.util.LinkedList;
import java.util.Optional;

public interface PersonalService {

    void registrarPersonal(Personal personal);

    LinkedList<Personal> listarTodoElPersonal();

    Optional<Personal> findById(int id);

    void actualizarPersonal(Personal personal);

    void eliminarAgente(int id);
}
