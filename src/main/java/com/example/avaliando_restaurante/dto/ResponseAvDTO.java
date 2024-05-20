package com.example.avaliando_restaurante.dto;

import com.example.avaliando_restaurante.domain.Avaliacao;

public record ResponseAvDTO(String restaurante,Double nota,String descricao,String cliente) {

	public ResponseAvDTO(Avaliacao body) {
		this(body.getRestaurante().getName(),body.getNota(),body.getDescricao(),body.getCliente().getName());
	}

}
