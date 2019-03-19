package com.stefanini.teste.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stefanini.projeto.model.Aluno;
import com.stefanini.projeto.model.Mochila;
import com.stefanini.projeto.repository.AlunoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoRepositoryTest {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Test
	public void findAluno() {
		List<Aluno> alunos = alunoRepository.findAll();
		assertThat(alunos.size()).isEqualTo(2);
		assertThat(alunos.get(0).getNome().equals("César Lucas Júnior"));
	}
	
	@Test
	public void insertAluno() {
		Mochila mochila = new Mochila("Nike");
		Aluno aluno = new Aluno("César", "A");
		List<Mochila> mochilas = new ArrayList<Mochila>();
		mochilas.add(mochila);
		aluno.setMochilas(mochilas);
		
		Aluno alunoRetorno = alunoRepository.save(aluno);
		
		assertThat(alunoRetorno.getNome().equals("César"));
		assertThat(alunoRetorno.getTurma().equals("A"));
		assertThat(alunoRetorno.getMochilas().get(0).getMarca().equals("Nike"));
	}
	
	@Test
	public void updateAluno() {
		Mochila mochila = new Mochila("Adidas");
		Aluno aluno = new Aluno();
		aluno.setId(1L);
		aluno.setNome("Lucas");
		aluno.setTurma("A");
		List<Mochila> mochilas = new ArrayList<Mochila>();
		mochilas.add(mochila);
		aluno.setMochilas(mochilas);
		
		Aluno alunoAtualizado = alunoRepository.save(aluno);
		
		assertThat(alunoAtualizado.getNome().equals(aluno.getNome()));
		assertThat(alunoAtualizado.getTurma().equals(aluno.getTurma()));
		assertThat(alunoAtualizado.getMochilas().get(0).getMarca().equals("Adidas"));
	}
}
