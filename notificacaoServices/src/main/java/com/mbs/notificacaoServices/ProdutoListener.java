package com.mbs.notificacaoServices;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mbs.notificacaoEntidades.EventoEmail;

@Component
public class ProdutoListener {

	@Value("${queue}")
	String queue;
	
	private Gson gson = new Gson();
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@RabbitListener(queues = {"${queue}"})
    public void receive(@Payload String fileBody) {
		System.out.println("Mensagem recebida da fila " + queue + ": " +  fileBody);
		EventoEmail evento = gson.fromJson(fileBody, EventoEmail.class);
		
		System.out.println("bgl do cliente" + evento.getCliente().getEmail());
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		 message.setFrom("jklasjhklsadçjkl"); //TODO
		 message.setTo(evento.getCliente().getEmail()); 
		 message.setSubject(evento.getTituloEmail()); 
		 message.setText(evento.getTextoEmail());
		 javaMailSender.send(message);
		
		 }
}
