package com.example.avaliando_restaurante.dto;

import com.example.avaliando_restaurante.domain.Avaliacao;

public record AvRestauranteDTO(Double nota,String descricao,String cliente) {
	public AvRestauranteDTO(Avaliacao body) {
		this(body.getNota(),body.getDescricao(),body.getCliente().getName());
	}
}
