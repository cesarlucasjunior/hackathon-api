package com.stefanini;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.stefanini.projeto.model.Aluno;
import com.stefanini.projeto.model.Mochila;
import com.stefanini.projeto.repository.AlunoRepository;
import com.stefanini.projeto.repository.MochilaRepository;

@SpringBootApplication
public class App extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	public AlunoRepository alunoRepository;
	@Autowired
	public MochilaRepository mochilaRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void run(String... args) throws Exception {

		Mochila mochila1 = new Mochila("Adidas");
		Mochila mochila2 = new Mochila("Nike");
		Aluno aluno = new Aluno("César Lucas Júnior", "A");
		
		
		aluno.setMochilas(Arrays.asList(mochila1, mochila2));

		mochila1.setAluno(aluno);
		mochila2.setAluno(aluno);

		// Pesistindo no banco de dados:

		System.out.println("Persisitindo dados...");

		alunoRepository.saveAll(Arrays.asList(aluno));
		mochilaRepository.saveAll(Arrays.asList(mochila1, mochila2));

	}
}
