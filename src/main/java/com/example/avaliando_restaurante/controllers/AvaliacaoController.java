package com.example.avaliando_restaurante.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.avaliando_restaurante.domain.Avaliacao;
import com.example.avaliando_restaurante.dto.AvaliacaoDTO;
import com.example.avaliando_restaurante.repositories.AvaliacaoRepository;

@RestController
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoController {
	@Autowired
	private AvaliacaoRepository repository;

	@GetMapping
	public ResponseEntity<List<AvaliacaoDTO>> findAll() {
		List<Avaliacao> list = repository.findAll();
		List<AvaliacaoDTO> listDto= list.stream().map(x -> new AvaliacaoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<AvaliacaoDTO> findById(@PathVariable String id) {
		Optional<Avaliacao> avaliacao = repository.findById(id);
		if(avaliacao.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(new AvaliacaoDTO(avaliacao));
	}

	@PostMapping
	public ResponseEntity<Void> register(@RequestBody Avaliacao body) {
		Optional<Avaliacao> av = this.repository.findById(body.getId());
      if(av.isEmpty()) {
            Avaliacao newAv = new Avaliacao();
            newAv.setCliente(body.getCliente());
            newAv.setDescricao(body.getDescricao());
            newAv.setNota(body.getNota());
            newAv.setRestaurante(body.getRestaurante());
            this.repository.save(newAv);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
