package com.example.avaliando_restaurante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.avaliando_restaurante.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
