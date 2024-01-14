package com.wallet.app.repository;

import java.util.List;

public interface Crud<T, ID> {
    T getById(ID id);

    List<T> findAll();

    List<T> saveAll(List<T> toSave);

    T save(T toSave);

    void removeById(ID id);
}
