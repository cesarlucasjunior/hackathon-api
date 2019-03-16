package com.stefanini.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.stefanini.projeto.exception.DataIntegrityException;
import com.stefanini.projeto.exception.InstanceNumberExceeded;
import com.stefanini.projeto.exception.ObjectNotFoundException;
import com.stefanini.projeto.model.Aluno;
import com.stefanini.projeto.model.Mochila;
import com.stefanini.projeto.repository.AlunoRepository;
import com.stefanini.projeto.repository.MochilaRepository;

@Service
public class MochilaService {

	@Autowired
	private MochilaRepository mochilaRepository;
	@Autowired
	private AlunoRepository alunoRepository;

	public Mochila find(Long id) {
		Optional<Mochila> mochilaOptional = mochilaRepository.findById(id);

		return mochilaOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Mochila.class.getName()));
	}

	public List<Mochila> findAll() {
		return mochilaRepository.findAll();
	}

	@Transactional
	public Mochila insert(Mochila mochila) {
		mochila.setId(null);
		System.out.println(mochila.getAluno().getId());
		Optional<Aluno> alunoOptional = alunoRepository.findById(mochila.getAluno().getId());
		
		if(alunoOptional.get().getId() == null) {
			throw new ObjectNotFoundException("Uma mochila precisa, obrigatoriamente, de um aluno vinculado!");
		} else if(alunoOptional.get().getMochilas().size() > 4){
			throw new InstanceNumberExceeded("Só podem, no máximo 5 mochilas por usuário!");
		} else {
		
			Aluno aluno = new Aluno();
			aluno.setId(alunoOptional.get().getId());
			aluno.setNome(alunoOptional.get().getNome());
			aluno.setTurma(alunoOptional.get().getTurma());		
			
			mochila.setAluno(aluno);
		
			return mochilaRepository.save(mochila);
		}
	}

	@Transactional
	public Mochila update(Mochila mochila) {
		find(mochila.getId());
		return mochilaRepository.save(mochila);
	}

	public void delete(Long id) {
		find(id);
		try {
			mochilaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir essa mochila porque ela possui um aluno associado!");
		}
	}

}
