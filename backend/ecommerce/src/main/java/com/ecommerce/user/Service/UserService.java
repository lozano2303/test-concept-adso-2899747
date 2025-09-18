package com.ecommerce.user.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Service.ABaseService;
import com.ecommerce.user.Entity.User;
import com.ecommerce.user.IRepository.IUserRepository;
import com.ecommerce.user.IService.IUserService;

/**
 * Implementación del servicio para la entidad User.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class UserService extends ABaseService<User, Long> implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}