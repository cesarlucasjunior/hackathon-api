package com.stefanini.teste.repository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoResourceTest {

	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setupInicial() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testGetByNome() throws Exception {
		String url ="/aluno/Cesar Lucas Junior";
		this.mvc.perform(get(url))
			.andExpect(status().isOk());
	}
	
	@Test
	public void deleteAluno() throws Exception {
		String url ="/aluno/1";
		this.mvc.perform(delete(url))
			.andExpect(status().isNoContent());
	}
	
	@Test
	public void insertAlunos() throws Exception {
		String url = "/aluno";
		this.mvc.perform(post(url)
			.content("{\"nome\": \"Cesar Jr\",\"turma\": \"A\",\"mochilas\": [{\"marca\" : \"Nike\"}]}")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());			
	}
	
	
	public void updateAluno() throws Exception{
		String url = "/aluno";
		this.mvc.perform(put(url)
		.content("{\"id\": \"1\",\"nome\": \"Marcela\",\"turma\": \"B\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());		
	}	
}
