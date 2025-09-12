package com.mbs.vendasServices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbs.vendasServices.entidades.Venda;

@CrossOrigin(origins = "http://localhost:9005")
@Controller
public class VendasControllerAPI {

	private List<Venda> listaProduto = new ArrayList<Venda>();
	
	@RequestMapping(value = "/v1/venda", method = RequestMethod.POST)
	public ResponseEntity<String> salvar(@RequestBody Venda venda) {
		System.out.println("executando salvar " + venda);
		// simples validacao de negocio
		if (venda.getCodCliente() == null || (venda.getPrecoProduto() != null && venda.getNomeProduto().length() <= 2)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Nome do cliente deve ter no minimo 3 caracteres");
		}
		// adiciona na lista
		listaProduto.add(venda);
		System.out.println(listaProduto);
		// retorna para o cliente o status ok e o id do cliente cadastrado.
		return ResponseEntity.ok(venda.getCodCliente().toString());
	}
	
	@RequestMapping(value = "/v1/venda", method = RequestMethod.GET)
	public ResponseEntity<List<Venda>> listar() {
		System.out.println("executando listar ");
		// retorna a lista de clientes
		return ResponseEntity.ok(listaProduto);
	}
	
	@RequestMapping(value = "/v1/venda/total_vendas", method = RequestMethod.GET)
	public ResponseEntity<Double> buscarCliente(Venda venda) {
		System.out.println("Verificando o total de vendas");
		for (Venda v : listaProduto) {
			if (venda.getPrecoProduto() != null) {
				double total = venda.getPrecoProduto() + venda.getPrecoProduto();
				return ResponseEntity.ok(total);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}
