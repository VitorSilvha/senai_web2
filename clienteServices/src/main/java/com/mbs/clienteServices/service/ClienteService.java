package com.mbs.clienteServices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mbs.clienteServices.entidades.Cliente;
import com.mbs.clienteServices.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;// injeção de dependência 
	
	public String salvar(Cliente cliente) throws Exception {
		System.out.println("executando salvar na service: " + cliente);
		
		// simples validacao de negocio
		if(cliente.getNome() == null || ( cliente.getNome() != null && cliente.getNome().length() <=2 )){
			throw new Exception("Nome do cliente deve ter "
					+ "no minimo 3 caracteres");
		}
		
		return clienteRepository.salvar(cliente);		
	}
		
	public List<Cliente> listar() {
		return clienteRepository.listar();
	}
	
	
	public boolean deletar(Integer id) {
		return clienteRepository.deletar(id);
	}
	
	
	public boolean atualizar(Cliente cliente) {
		return clienteRepository.atualizar(cliente);		
	}
	
	
	public Boolean existeCliente(Integer id) { 
		return clienteRepository.existeCliente(id);				
	}
	
	
	public Cliente buscarCliente(Integer id) { 
		return clienteRepository.buscarCliente(id);			
	}

}