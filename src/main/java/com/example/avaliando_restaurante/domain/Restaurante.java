package com.example.avaliando_restaurante.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.avaliando_restaurante.dto.AvRestauranteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Avaliacao> avaliacoes=new ArrayList<>();
	public Restaurante(String id,String name) {
		this.id=id;
		this.name=name;
	}
	public List<AvRestauranteDTO> avsDTO(){
		List<AvRestauranteDTO> listDto=avaliacoes.stream().map(x -> new AvRestauranteDTO(x)).collect(Collectors.toList());
		return listDto;
	}
}
