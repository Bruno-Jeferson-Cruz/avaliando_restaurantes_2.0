package com.example.avaliando_restaurante.dto;

import com.example.avaliando_restaurante.domain.User;

public record UserVisibleDTO(String name) {

	public UserVisibleDTO(User x) {
		this(x.getName());	
		}
}
