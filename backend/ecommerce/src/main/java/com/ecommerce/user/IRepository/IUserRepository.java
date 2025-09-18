package com.ecommerce.user.IRepository;

import java.util.Optional;

import com.ecommerce.common.IRepository.IBaseRepository;
import com.ecommerce.user.Entity.User;

/**
 * Interfaz de repositorio para la entidad User.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface IUserRepository extends IBaseRepository<User, Long> {

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