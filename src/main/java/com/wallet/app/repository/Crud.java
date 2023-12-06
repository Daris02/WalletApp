package com.wallet.app.repository;

import java.util.List;

public interface Crud<T> {
    T getById(String id);

    List<T> findAll();

    List<T> saveAll(List<T> toSave);

    T save(T toSave);
}
