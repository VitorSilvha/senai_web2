package com.mbs.vendasServices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.vendasServices.entidades.Venda;
import com.mbs.vendasServices.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository; // Injeção de Dependência
	private Double menor;
	private Double maior;

	public String salvarVenda(Venda venda) throws Exception {
		System.out.println("executando salvar na Service: " + venda);

		// simples validacao de negocio
		if (venda.getNomeProduto() == null
				|| (venda.getNomeProduto() != null && venda.getNomeProduto().length() <= 2)) {
			throw new Exception("Nome do venda deve ter no minimo 3 caracteres");
		}
		return vendaRepository.salvarVenda(venda);
	}

	public List<Venda> listarVenda() {
		return vendaRepository.listarVenda();
	}

	public Double menorVenda() {
		List<Venda> listaVenda = vendaRepository.listarVenda();
		int count = 0;
		for (Venda vendas : listaVenda) {
			if (count == 0) {
				menor = vendas.getPrecoProduto();
				count++;
			} else {
				if (vendas.getPrecoProduto() < menor) {
					menor = vendas.getPrecoProduto();
				}
			}
		}
		return menor;
	}

	public Double maiorVenda() {
		List<Venda> listaVenda = vendaRepository.listarVenda();
		int count = 0;
		for (Venda vendas : listaVenda) {
			if (count == 0) {
				maior = vendas.getPrecoProduto();
				count++;
			} else {
				if (vendas.getPrecoProduto() > maior) {
					maior = vendas.getPrecoProduto();
				}
			}
		}
		return maior;
	}
	
	public Double totalVenda() {
		List<Venda> listaVenda = vendaRepository.listarVenda();
		Double total = 0.0;
		for (Venda vendas : listaVenda) {
			total = total + vendas.getPrecoProduto();
		}
		return total;
	}

}
