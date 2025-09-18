package com.ecommerce.user.IService;

import java.util.Optional;

import com.ecommerce.common.IService.IBaseService;
import com.ecommerce.user.Entity.Role;

/**
 * Interfaz de servicio para la entidad Role.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface IRoleService extends IBaseService<Role, Long> {

    /**
     * Busca un rol por su nombre.
     * @param nombre El nombre del rol.
     * @return Un Optional con el rol si existe.
     */
    Optional<Role> findByNombre(String nombre);

    /**
     * Verifica si existe un rol con el nombre dado.
     * @param nombre El nombre a verificar.
     * @return true si existe, false en caso contrario.
     */
    boolean existsByNombre(String nombre);
}