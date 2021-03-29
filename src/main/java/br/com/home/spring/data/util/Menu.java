package br.com.home.spring.data.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu {
	
	public static void exibirMenu(List<String> opcoes) {
		
		AtomicInteger numeroOpcao = new AtomicInteger(0);
		
		opcoes.forEach(s -> {
			System.out.println(String.format("%s - %s",String.valueOf(numeroOpcao.getAndIncrement()), s));
		});
	}
}
