package com.stefanini.projeto.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.stefanini.projeto.exception.DataIntegrityException;
import com.stefanini.projeto.exception.ObjectNotFoundException;
import com.stefanini.projeto.model.Aluno;
import com.stefanini.projeto.model.Mochila;
import com.stefanini.projeto.repository.AlunoRepository;
import com.stefanini.projeto.repository.MochilaRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private MochilaRepository mochilaRepository;

	// Busca aluno por id e caso não encontre retorna uma exception personalizada.
	public Aluno find(Long id) {

		Optional<Aluno> alunoOptional = alunoRepository.findById(id);

		return alunoOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Aluno.class.getName()));
	}

	// findByAluno
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
			System.out.println("----------------------Nome--------------------" + alunoValidacao.getNome());
			throw new DataIntegrityException("Erro! Já existe um aluno com esse nome!");
		}else {	
			for(Iterator<Mochila> iterator = aluno.getMochilas().iterator();iterator.hasNext();){
				Mochila mochilaDoAluno = iterator.next();
				mochilaDoAluno.setAluno(aluno);
				mochilaRepository.save(mochilaDoAluno);
			}
			return alunoRepository.save(aluno);
		}
	}

	@Transactional
	public Aluno update(Aluno aluno) {
		Optional<Aluno> alunoFind  = alunoRepository.findById(aluno.getId());
		alunoFind.get().setNome(aluno.getNome());
		alunoFind.get().setTurma(aluno.getTurma());
		return alunoRepository.save(alunoFind.get());
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
