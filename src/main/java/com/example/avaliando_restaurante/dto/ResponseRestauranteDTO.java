package com.example.avaliando_restaurante.dto;

import java.util.List;

import com.example.avaliando_restaurante.domain.Restaurante;

public record ResponseRestauranteDTO(String name,List<AvRestauranteDTO> avaliacoes) {
	public ResponseRestauranteDTO(Restaurante r) {
		this(r.getName(),r.avsDTO());
		}
}
