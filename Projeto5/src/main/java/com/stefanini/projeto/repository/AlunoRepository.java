package com.stefanini.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanini.projeto.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
