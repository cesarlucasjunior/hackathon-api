package com.stefanini.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanini.projeto.model.Aluno;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
	
	public Aluno findByNome(String nome);
}
