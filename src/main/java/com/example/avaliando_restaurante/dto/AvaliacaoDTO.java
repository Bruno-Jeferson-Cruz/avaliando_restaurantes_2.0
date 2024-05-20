package com.example.avaliando_restaurante.dto;

import java.util.Optional;

import com.example.avaliando_restaurante.domain.Avaliacao;

public record AvaliacaoDTO(Double nota,String descricao,String cliente,String restaurante){

	public AvaliacaoDTO(Avaliacao av) {
		this(av.getNota(), av.getDescricao(),av.getCliente().getName(),av.getRestaurante().getName());
	}
	public AvaliacaoDTO(Optional<Avaliacao> av) {
		this(av.get().getNota(), av.get().getDescricao(), av.get().getCliente().getName(), av.get().getRestaurante().getName());
	}
}
