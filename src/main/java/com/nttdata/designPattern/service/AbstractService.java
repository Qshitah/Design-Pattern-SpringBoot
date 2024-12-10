package com.nttdata.designPattern.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T, ID> {

    // Abstract methods to be implemented by subclasses for entity-specific logic
    protected abstract JpaRepository<T, ID> getRepository();

    // Create (Save)
    @Transactional
    public T create(T entity) {
        return getRepository().save(entity);
    }

    // Read (Find by ID)
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    // Read all (Find all)
    public List<T> findAll() {
        return getRepository().findAll();
    }

    // Update (Save with existing entity)
    @Transactional
    public T update(T entity) {
        return getRepository().save(entity);
    }

    // Delete (Delete by ID)
    @Transactional
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    // Delete all
    @Transactional
    public void deleteAll() {
        getRepository().deleteAll();
    }
}
