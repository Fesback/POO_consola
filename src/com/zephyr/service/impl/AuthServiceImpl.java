package com.zephyr.service.impl;

import com.zephyr.dao.PersonalDAO;
import com.zephyr.entity.Personal;
import com.zephyr.service.AuthService;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final PersonalDAO personalDAO;

    public AuthServiceImpl(PersonalDAO personalDAO) {
        this.personalDAO = personalDAO;
    }

    @Override
    public Optional<Personal> login(String correo, String contrasena) {
        Optional<Personal> personalOptional = personalDAO.findByCorreo(correo);
        if (personalOptional.isEmpty()) {
            return Optional.empty();
        }

        Personal personal = personalOptional.get();
        if (personal.getContrasena().equals(contrasena)) {
            return Optional.of(personal);
        } else {
            return Optional.empty();
        }
    }
}
