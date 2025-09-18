package com.ecommerce.user.IService;

import java.util.Optional;

import com.ecommerce.common.IService.IBaseService;
import com.ecommerce.user.Entity.User;

/**
 * Interfaz de servicio para la entidad User.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface IUserService extends IBaseService<User, Long> {

    /**
     * Busca un usuario por su email.
     * @param email El email del usuario.
     * @return Un Optional con el usuario si existe.
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica si existe un usuario con el email dado.
     * @param email El email a verificar.
     * @return true si existe, false en caso contrario.
     */
    boolean existsByEmail(String email);
}