package com.example.avaliando_restaurante.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.example.avaliando_restaurante.dto.UserVisibleDTO;
import com.example.avaliando_restaurante.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository repository;
    @GetMapping
    public ResponseEntity<List<UserVisibleDTO>> findall(){
    	List<User> list=repository.findAll();
    	List<UserVisibleDTO> listDto= list.stream().map(x -> new UserVisibleDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<UserVisibleDTO> findById(@PathVariable String id){
    	Optional<User> user=repository.findById(id);
    	if(user.isEmpty()) {
    		return ResponseEntity.badRequest().build();
    	}
    	UserVisibleDTO userDto=new UserVisibleDTO(user.get().getName());
    	return ResponseEntity.ok().body(userDto);
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
