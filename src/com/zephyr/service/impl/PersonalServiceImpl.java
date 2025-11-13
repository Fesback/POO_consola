package com.zephyr.service.impl;

import com.zephyr.dao.PersonalDAO;
import com.zephyr.entity.Personal;
import com.zephyr.service.PersonalService;

import java.util.LinkedList;
import java.util.Optional;

public class PersonalServiceImpl implements PersonalService {

    private final PersonalDAO personalDAO;

    public PersonalServiceImpl(PersonalDAO personalDAO) {
        this.personalDAO = personalDAO;
    }

    @Override
    public void registrarPersonal(Personal personal) {
        System.out.println("[Service] Lógica de 'registrar' ejecutada.");
        personalDAO.create(personal);
    }

    @Override
    public LinkedList<Personal> listarTodoElPersonal() {
        System.out.println("[Service] Lógica de 'listar' ejecutada.");
        return personalDAO.list();
    }

    @Override
    public Optional<Personal> findById(int id) {
        System.out.println("[Service] Lógica de 'buscar por ID' ejecutada.");
        return personalDAO.read(id);
    }

    @Override
    public void actualizarPersonal(Personal personal) {
        System.out.println("[Service] Lógica de 'actualizar' ejecutada.");
        personalDAO.update(personal);
    }

    @Override
    public void eliminarAgente(int id) {
        System.out.println("[Service] Lógica de 'eliminar' ejecutada.");
        personalDAO.delete(id);
    }
}
