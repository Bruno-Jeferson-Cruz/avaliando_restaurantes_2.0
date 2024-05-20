package com.example.avaliando_restaurante.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.avaliando_restaurante.domain.Avaliacao;
import com.example.avaliando_restaurante.domain.Restaurante;
import com.example.avaliando_restaurante.domain.User;
import com.example.avaliando_restaurante.repositories.AvaliacaoRepository;
import com.example.avaliando_restaurante.repositories.RestauranteRepository;
import com.example.avaliando_restaurante.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class TestConfig implements CommandLineRunner{
	private final UserRepository userRespository;
	private final AvaliacaoRepository avaliacaoRepository;
	private final RestauranteRepository restauranteRepository;
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args)throws Exception {
		userRespository.deleteAll();
		avaliacaoRepository.deleteAll();
		restauranteRepository.deleteAll();
		
		User bruno=new User(null,"bruno","bruno@gmail.com","1234");
		bruno.setPassword(passwordEncoder.encode(bruno.getPassword()));
		User maria=new User(null,"maria","maria@gmail.com","1234");
		maria.setPassword(passwordEncoder.encode(maria.getPassword()));
		User nayla=new User(null,"nayla","nayla@gmail.com","1234");
		nayla.setPassword(passwordEncoder.encode(nayla.getPassword()));
		userRespository.saveAll(Arrays.asList(bruno,maria,nayla));
		
		Restaurante r1=new Restaurante(null,"Siri Cascudo");
		Restaurante r2=new Restaurante(null,"Pizza Planet");
		Restaurante r3=new Restaurante(null,"Gusteau's");
		restauranteRepository.saveAll(Arrays.asList(r1,r2,r3));	
		
		Avaliacao av1=new Avaliacao(null,4.5,"Muito boa!",bruno,r1);
		Avaliacao av2=new Avaliacao(null,3.0,"Demorou muito!",maria,r2);
		Avaliacao av3=new Avaliacao(null,4.0,"Demorou mas estava boa!",nayla,r3);
		Avaliacao av4=new Avaliacao(null,5.0,"Incrivel!",nayla,r1);
		avaliacaoRepository.saveAll(Arrays.asList(av1,av2,av3,av4));
		
		bruno.setAvaliacoes(Arrays.asList(av1));
		maria.setAvaliacoes(Arrays.asList(av2));
		nayla.setAvaliacoes(Arrays.asList(av3,av4));
		userRespository.saveAll(Arrays.asList(bruno,maria,nayla));
		
		r1.setAvaliacoes(Arrays.asList(av1,av4));
		r2.setAvaliacoes(Arrays.asList(av2));
		r3.setAvaliacoes(Arrays.asList(av3));
		restauranteRepository.saveAll(Arrays.asList(r1,r2,r3));	
	}
	
	
	
	
}
