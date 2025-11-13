package com.zephyr.core;

import java.util.LinkedList;
import java.util.Optional;

public interface Crud<T> {
    //create
    void create(T obj);

    //read ID
    Optional<T> read(int id);

    // update
    void update(T obj);

    //delete
    void delete(int id);

    // read all
    LinkedList<T> list();
}
