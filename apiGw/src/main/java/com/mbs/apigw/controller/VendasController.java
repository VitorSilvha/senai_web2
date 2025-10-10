package com.mbs.apigw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.apigw.entidades.Venda;
import com.mbs.apigw.comunicacao.VendasRoteamento;

@RestController
public class VendasController {
	
	@Autowired
	private VendasRoteamento vendasRoteamento;
	
	@RequestMapping(value = "/v1/api-gw/venda", method = RequestMethod.POST)
	public ResponseEntity<String> salvarVendas(@RequestBody Venda venda) {
		System.out.println("Execultando salvar");
		return vendasRoteamento.salvarVendas(venda); // chamando vendas service
	}

}
