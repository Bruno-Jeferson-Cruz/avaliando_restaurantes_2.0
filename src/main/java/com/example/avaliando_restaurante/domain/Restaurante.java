package com.example.avaliando_restaurante.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	@OneToMany(mappedBy = "restaurante")
	private List<Avaliacao> avaliacoes=new ArrayList<>();

}
