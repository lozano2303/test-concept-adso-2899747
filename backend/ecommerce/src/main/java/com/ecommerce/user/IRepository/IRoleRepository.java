package com.ecommerce.user.IRepository;

import java.util.Optional;

import com.ecommerce.common.IRepository.IBaseRepository;
import com.ecommerce.user.Entity.Role;

/**
 * Interfaz de repositorio para la entidad Role.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface IRoleRepository extends IBaseRepository<Role, Long> {

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