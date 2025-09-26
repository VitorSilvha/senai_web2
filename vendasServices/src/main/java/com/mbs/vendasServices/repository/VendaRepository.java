package com.mbs.vendasServices.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mbs.vendasServices.entidades.Venda;

@Repository
public class VendaRepository {

	private ArrayList<Venda> lista = new ArrayList<Venda>();
	private static Integer id = 0;
	

	public String salvarVenda(Venda venda) throws Exception {
		System.out.println("executando salvar no Repository: " + venda);
		venda.setNumeroVenda(id);
		id++;
		lista.add(venda);
		return venda.getNumeroVenda().toString();
	}

	public List<Venda> listarVenda() {
		return lista;
	}
	
}
