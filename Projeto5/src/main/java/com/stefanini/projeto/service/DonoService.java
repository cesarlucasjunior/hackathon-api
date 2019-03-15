package com.stefanini.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.projeto.exception.TreinaException;
import com.stefanini.projeto.model.Dono;
import com.stefanini.projeto.repository.DonoRepository;

@Service
public class DonoService {

	@Autowired
	private DonoRepository repository;
	
	public List<Dono> findAll() throws TreinaException {
		return (List<Dono>) repository.findAll();
	}

}