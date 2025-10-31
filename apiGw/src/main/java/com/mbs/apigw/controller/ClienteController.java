package com.mbs.apigw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.apigw.comunicacao.ClienteRoteamento;
import com.mbs.apigw.entidades.Cliente;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRoteamento clienteRoteamento;
	
	
	@RequestMapping(value = "/v1/cliente",method = RequestMethod.POST)
	public ResponseEntity<String>salvar(@RequestBody Cliente cliente) {
		System.out.println("Executando -salvar cliente-");
		System.out.println("Chamando o cliente service; endpoint salvar");
		ResponseEntity<String> resultado = clienteRoteamento.salvar(cliente);
		return resultado;	
	}
	
	//TODO atualizar os endpoints
	@RequestMapping(value = "/v1/cliente/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		System.out.println("executando deletar de cliente id " + id );
		ResponseEntity<Void> resultado = clienteRoteamento.deletar(id);
		return resultado;
	}
	
	@RequestMapping(value = "/v1/cliente",method = RequestMethod.PUT)
	public ResponseEntity<String> atualizar(@RequestBody Cliente cliente) {
		ResponseEntity<String> resultado = clienteRoteamento.atualizar(cliente);
		return resultado;
	}
	
	@RequestMapping(value = "/v1/api-gw/cliente/existe-cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> existeCliente(@PathVariable Integer id) {
		System.out.println("executando -existe cliente-");
		// chamando o cliente-service
		System.out.println("Chamando cliente service; endpoint existe-cliente" );
		ResponseEntity<Boolean> resultado = clienteRoteamento.existeCliente(id);
		System.out.println("Resultado clienteService" + resultado.getBody());
		return resultado;
	}
	
	@RequestMapping(value = "/v1/api-gw/cliente/buscar-cliente/{id}",method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id) {
		ResponseEntity<Cliente> resultado = clienteRoteamento.buscarCliente(id);
		return resultado;
		
	}
}
