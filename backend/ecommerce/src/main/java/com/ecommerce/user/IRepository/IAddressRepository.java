package com.ecommerce.user.IRepository;

import java.util.List;

import com.ecommerce.common.IRepository.IBaseRepository;
import com.ecommerce.user.Entity.Address;

/**
 * Interfaz de repositorio para la entidad Address.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface IAddressRepository extends IBaseRepository<Address, Long> {

    /**
     * Busca todas las direcciones de un usuario específico.
     * @param userId El ID del usuario.
     * @return Una lista de direcciones del usuario.
     */
    List<Address> findByUserId(Long userId);
}