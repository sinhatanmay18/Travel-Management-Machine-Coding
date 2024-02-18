package org.travel.packagemanagement.repository;

import org.travel.packagemanagement.exceptions.ActivityNotFoundException;
import org.travel.packagemanagement.exceptions.DestinationNotFoundException;
import org.travel.packagemanagement.exceptions.PackageNotFoundException;

import java.util.List;

public interface CrudRepository<T> {
    void save(T entity);

    void saveAll(List<T> entity);

    T find(String identifier) throws Exception;

    List<T> findAll();

    T remove(String identifier);
}
