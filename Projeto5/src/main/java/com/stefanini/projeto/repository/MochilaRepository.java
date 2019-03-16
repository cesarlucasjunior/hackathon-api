package com.stefanini.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanini.projeto.model.Mochila;

public interface MochilaRepository extends JpaRepository<Mochila, Long> {

	public Long countByMarca(String marca);
}
