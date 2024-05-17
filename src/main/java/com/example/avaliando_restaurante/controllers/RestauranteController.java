package com.example.avaliando_restaurante.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.avaliando_restaurante.domain.Restaurante;
import com.example.avaliando_restaurante.repositories.RestauranteRepository;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	@Autowired
	private RestauranteRepository repository;

	@GetMapping
	public ResponseEntity<List<Restaurante>> findAll() {
		List<Restaurante> list = repository.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<Optional<Restaurante>> findById(@PathVariable String id) {
		Optional<Restaurante> restaurante = repository.findById(id);
		if(restaurante.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(restaurante);
	}

	@PostMapping
	public ResponseEntity<Void> register(@RequestBody Restaurante body) {
		Optional<Restaurante> restaurante = this.repository.findByName(body.getName());

        if(restaurante.isEmpty()) {
            Restaurante newRes = new Restaurante();
            newRes.setName(body.getName());
            this.repository.save(newRes);
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