package com.stefanini.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.stefanini.projeto.exception.DataIntegrityException;
import com.stefanini.projeto.exception.ObjectNotFoundException;
import com.stefanini.projeto.model.Aluno;
import com.stefanini.projeto.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	// Busca aluno por id e caso não encontre retorna uma exception personalizada.
	public Aluno find(Long id) {

		Optional<Aluno> alunoOptional = alunoRepository.findById(id);

		return alunoOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Aluno.class.getName()));
	}

	// findByName
	public List<Aluno>findByAluno(String nome){
		return alunoRepository.findByNomeContainingIgnoreCase(nome);
	}

	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	@Transactional
	public Aluno insert(Aluno aluno) {
		Aluno alunoValidacao = alunoRepository.findByNome(aluno.getNome());		
		if(alunoValidacao!= null) {
			throw new DataIntegrityException("Erro! Já existe um aluno com esse nome!");
		}else {	
			return alunoRepository.save(aluno);
		}
	}

	@Transactional
	public Aluno update(Aluno aluno) {
		find(aluno.getId());
		return alunoRepository.save(aluno);
	}

	public void delete(Long id) {
		find(id);
		try {
			alunoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir esse aluno porque ele possui um mochila associada!");
		}
	}
}
