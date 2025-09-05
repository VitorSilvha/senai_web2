package com.mbs.clienteServices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mbs.clienteServices.entidades.Cliente;

@Controller
@CrossOrigin(origins = "http://localhost:9005")
public class ClienteControllerAPI {

	private List<Cliente> listaCliente = new ArrayList<Cliente>();
	private static Integer id = 0;
	
	
	@RequestMapping(value = "/v1/cliente",method = RequestMethod.POST)
	public ResponseEntity<String> salvar(@RequestBody Cliente cliente) {
		System.out.println("executando salvar " + cliente);
		
		// simples validacao de negocio
		if(cliente.getNome() == null || ( cliente.getNome() != null && cliente.getNome().length() <=2 )){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST). body("Nome do cliente deve ter no minimo 3 caracteres");
		}
		// cria um id para o cliente
		cliente.setId(++id);
		// adiciona na lista
		listaCliente.add(cliente);
		//retorna para o cliente o status ok e o id do cliente cadastrado.
		return ResponseEntity.ok(id.toString());
	}
	
	@RequestMapping(value = "/v1/cliente",method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listar() {
		System.out.println("executando listar " );
		// retorna a lista de clientes
		return ResponseEntity.ok(listaCliente);
	}
	
	@RequestMapping(value = "/v1/cliente/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		System.out.println("executando deletar de cliente id " + id );
		// deleta o cliente pelo id, caso ele exista
		boolean resultado = listaCliente.removeIf( (obj) ->  obj.getId().equals(id));
		if(resultado == true) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		// retorna a lista de clientes
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(value = "/v1/cliente/buscar", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> buscarCliente(
	    @RequestParam(required = false) String nome,
	    @RequestParam(required = false) String email,
	    @RequestParam(required = false) String cpf,
	    @RequestParam(required = false) String cep
	) {
	    System.out.println("Executando buscarCliente com filtros:");
	    System.out.println("nome: " + nome + ", email: " + email + ", cpf: " + cpf + ", cep: " + cep);

	    List<Cliente> resultado = new ArrayList<>();

	    for (Cliente c : listaCliente) {
	        boolean matches = true;

	        if (nome != null && !nome.isEmpty() && !c.getNome().toLowerCase().contains(nome.toLowerCase())) {
	            matches = false;
	        }

	        if (email != null && !email.isEmpty() && !c.getEmail().toLowerCase().contains(email.toLowerCase())) {
	            matches = false;
	        }

	        if (cpf != null && !cpf.isEmpty() && !c.getCpf().contains(cpf)) {
	            matches = false;
	        }

	        if (cep != null && !cep.isEmpty() && !c.getCep().contains(cep)) {
	            matches = false;
	        }

	        if (matches) {
	            resultado.add(c);
	        }
	    }

	    return ResponseEntity.ok(resultado);
	}

	// SEGUIR IMPLEMENTACAO
}
