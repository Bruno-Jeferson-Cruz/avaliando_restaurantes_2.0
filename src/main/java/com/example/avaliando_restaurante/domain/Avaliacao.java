package com.example.avaliando_restaurante.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "avaliacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private Double nota;
	private String descricao;
	@ManyToOne
    @JoinTable(name = "av_users",joinColumns = @JoinColumn(name = "av_id"), inverseJoinColumns = @JoinColumn(name = "cliente_id"))
	private User cliente;
	@ManyToOne
	@JoinTable(name = "avs_restaurantes", joinColumns = @JoinColumn(name = "av_id"), inverseJoinColumns = @JoinColumn(name = "restaurante_id"))
	private Restaurante restaurante;
}