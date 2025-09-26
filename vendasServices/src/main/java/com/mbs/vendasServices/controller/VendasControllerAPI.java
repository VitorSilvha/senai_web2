package com.mbs.vendasServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbs.vendasServices.entidades.Venda;
import com.mbs.vendasServices.service.VendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "http://localhost:9005")
@Controller
public class VendasControllerAPI {

	@Autowired
	private VendaService vendaService; // Injeção de dependência

	@Operation(summary = "Salva um venda")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro na validação dos campos") })
	@RequestMapping(value = "/v1/venda", method = RequestMethod.POST)
	public ResponseEntity<String> salvarVendas(@RequestBody Venda venda) {

		System.out.println("executando salvar na Controller: " + venda);

		try {
			String id = vendaService.salvarVenda(venda);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Operation(summary = "Retorna uma listagem de todas as vendas cadastradas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de vendas") })
	@RequestMapping(value = "/v1/venda", method = RequestMethod.GET)
	public ResponseEntity<List<Venda>> listarVendas() {
		List<Venda> resultado = vendaService.listarVenda();
		return ResponseEntity.ok(resultado);
	}

	@Operation(summary = "Mostra o total de vendas realizadas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o total de vendas"),
			@ApiResponse(responseCode = "400", description = "Não encontrou as vendas") })
	@RequestMapping(value = "/v1/total_venda", method = RequestMethod.GET)
	public ResponseEntity<Double> totalVenda() {
		Double resultado = vendaService.totalVenda();
		return ResponseEntity.ok(resultado);
	}

	@RequestMapping(value = "/v1/menor_venda", method = RequestMethod.GET)
	public ResponseEntity<Double> menorVenda() {
		Double resultado = vendaService.menorVenda();
		return ResponseEntity.ok(resultado);

	}

	@RequestMapping(value = "/v1/maior_venda", method = RequestMethod.GET)
	public ResponseEntity<Double> maiorVenda() {
		Double resultado = vendaService.maiorVenda();
		return ResponseEntity.ok(resultado);
	}

}
