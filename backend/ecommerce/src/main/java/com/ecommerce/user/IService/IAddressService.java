package com.ecommerce.user.IService;

import java.util.List;

import com.ecommerce.common.IService.IBaseService;
import com.ecommerce.user.Entity.Address;

/**
 * Interfaz de servicio para la entidad Address.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface IAddressService extends IBaseService<Address, Long> {

    /**
     * Busca todas las direcciones de un usuario específico.
     * @param userId El ID del usuario.
     * @return Una lista de direcciones del usuario.
     */
    List<Address> findByUserId(Long userId);
}