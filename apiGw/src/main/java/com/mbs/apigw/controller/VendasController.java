package com.mbs.apigw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.apigw.comunicacao.ClienteRoteamento;
import com.mbs.apigw.comunicacao.VendasRoteamento;
import com.mbs.apigw.entidades.Venda;

@RestController
public class VendasController {
	
	@Autowired
	private VendasRoteamento vendasRoteamento;
	
	@Autowired
	private ClienteRoteamento clienteRoteamento;
	
	@RequestMapping(value = "/v1/api-gw/venda", method = RequestMethod.POST)
	public ResponseEntity<String> salvarVendas(@RequestBody Venda venda) {
		System.out.println("Execultando salvar");
		return vendasRoteamento.salvarVendas(venda); // chamando vendas service
	}
	
	@RequestMapping(value = "/v1/api-gw/processar-venda", method = RequestMethod.POST)
	public ResponseEntity<String> processarVenda(@RequestBody Venda venda) {
		System.out.println("Processando a venda");
		System.out.println("Verificando se cliente existe");
		ResponseEntity<Boolean> existeCliente = clienteRoteamento.existeCliente(venda.getCodCliente());
		
		if(existeCliente.getBody().equals(Boolean.FALSE)) {
			System.out.println("Cliente não existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não existe");
		}
		System.out.println("Cliente existe");
		System.out.println("Processando salvar venda");
		ResponseEntity<String> salvarVenda = vendasRoteamento.salvarVendas(venda);
		if(salvarVenda.getStatusCode() == HttpStatus.BAD_REQUEST) {
			System.out.println("Erro ao salvar a venda");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar venda");
		}
		
		System.out.println("Salvar vendas realizada com sucesso");
		// TODO ajustar para a chamada correta do brocker
		System.out.println("MOCKANDO ENVIO DE MENSAGEM");
		return ResponseEntity.status(HttpStatus.OK).body("sucesso");
	}


}
