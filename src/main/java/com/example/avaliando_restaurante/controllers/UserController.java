package com.example.avaliando_restaurante.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.avaliando_restaurante.domain.User;
import com.example.avaliando_restaurante.dto.RegisterRequestDTO;
import com.example.avaliando_restaurante.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository repository;
    @GetMapping
    public ResponseEntity<List<User>> findall(){
    	List<User> list=repository.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable String id){
    	Optional<User> user=repository.findById(id);
    	return ResponseEntity.ok().body(user);
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
    	repository.deleteById(id);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping(value="/{id}")
	public ResponseEntity<String> update(@RequestBody RegisterRequestDTO body ,@PathVariable String id){
    	Optional<User> oldUser=repository.findById(id);
    	if(oldUser.isEmpty()) {
    		return ResponseEntity.badRequest().body("User dont found!");
    	}else {
    		oldUser.get().setName(body.name());
    		oldUser.get().setEmail(body.email());
    		oldUser.get().setPassword(passwordEncoder.encode(body.password()));
    		User newUser=oldUser.get();
    		repository.deleteById(id);
    		repository.save(newUser);
    		return ResponseEntity.noContent().build();
    		
    	}	
	}
}
