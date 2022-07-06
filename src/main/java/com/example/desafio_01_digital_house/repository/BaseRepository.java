package com.example.desafio_01_digital_house.repository;

import java.io.IOException;
import java.util.List;

public interface BaseRepository<K,V> {

    List<V> getAll();

    V getById(K key);

    void save(V value) throws IOException;

    void save(List<V> values) throws IOException;
}
