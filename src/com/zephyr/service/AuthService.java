package com.zephyr.service;

import com.zephyr.entity.Personal;

import java.util.Optional;

public interface AuthService {
    Optional<Personal> login(String correo, String contrasena);
}
