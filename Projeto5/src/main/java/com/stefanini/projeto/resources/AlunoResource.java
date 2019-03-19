package com.stefanini.projeto.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stefanini.projeto.model.Aluno;
import com.stefanini.projeto.service.AlunoService;
@CrossOrigin
@RestController
@RequestMapping(value="/aluno")
public class AlunoResource {

	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Aluno> insert(@RequestBody Aluno aluno){
		aluno = alunoService.insert(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Aluno>> findAll(){
		List<Aluno> listaAlunos = alunoService.findAll();
		return ResponseEntity.ok().body(listaAlunos);
	}
	
	@RequestMapping(value="/{nome}", method=RequestMethod.GET)
	public ResponseEntity<List<Aluno>> findByName(@PathVariable String nome){
		List<Aluno> alunos = alunoService.findByAluno(nome);
		return ResponseEntity.ok().body(alunos);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Aluno> update(@RequestBody Aluno aluno){
		aluno = alunoService.update(aluno);
		return ResponseEntity.ok().body(aluno);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Aluno> delete(@PathVariable Long id){
		alunoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
