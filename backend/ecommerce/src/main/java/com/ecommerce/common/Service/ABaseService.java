package com.ecommerce.common.Service;

import com.ecommerce.common.IRepository.IBaseRepository;
import com.ecommerce.common.IService.IBaseService;

import java.util.List;
import java.util.Optional;

/**
 * Implementación abstracta del servicio base.
 */
public abstract class ABaseService<T, ID> implements IBaseService<T, ID> {

    protected final IBaseRepository<T, ID> repository;

    public ABaseService(IBaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}