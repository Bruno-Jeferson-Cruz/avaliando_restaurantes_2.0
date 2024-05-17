package com.example.avaliando_restaurante.dto;

import java.util.Optional;

import com.example.avaliando_restaurante.domain.Avaliacao;
import com.example.avaliando_restaurante.domain.Restaurante;
import com.example.avaliando_restaurante.domain.User;

public record AvaliacaoDTO(Double nota,String descricao,User cliente,Restaurante restaurante){

	public AvaliacaoDTO(Avaliacao av) {
		this(av.getNota(), av.getDescricao(), av.getCliente(), av.getRestaurante());
	}
	public AvaliacaoDTO(Optional<Avaliacao> av) {
		this(av.get().getNota(), av.get().getDescricao(), av.get().getCliente(), av.get().getRestaurante());
	}
}
