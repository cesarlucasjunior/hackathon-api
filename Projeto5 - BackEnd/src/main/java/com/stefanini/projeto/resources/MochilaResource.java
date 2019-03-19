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

import com.stefanini.projeto.model.Mochila;
import com.stefanini.projeto.service.MochilaService;
@CrossOrigin
@RestController
@RequestMapping(value="/mochila")
public class MochilaResource {
	
	@Autowired
	private MochilaService mochilaService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Mochila> insert(@RequestBody Mochila mochila){
		mochila = mochilaService.insert(mochila);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mochila.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Mochila>> findAll(){
		List<Mochila> mochilas = mochilaService.findAll();
		return ResponseEntity.ok().body(mochilas);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Mochila> find(@PathVariable Long id){
		
		Mochila mochila = mochilaService.find(id);
		return ResponseEntity.ok().body(mochila);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Mochila> update(@RequestBody Mochila mochila){
		mochila = mochilaService.update(mochila);
		return ResponseEntity.ok().body(mochila);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Mochila> delete(@PathVariable Long id) {
		mochilaService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
