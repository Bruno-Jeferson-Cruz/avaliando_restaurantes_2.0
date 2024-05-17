package com.example.avaliando_restaurante.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.avaliando_restaurante.domain.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, String>{

	Optional<Restaurante> findByName(String name);
	
}