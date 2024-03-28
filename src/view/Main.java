package view;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Main {

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("#.##");
		double saldo; 
		double valor;
		int cod;
		Semaphore semaforo = new Semaphore(1);
		Semaphore semaforo2 = new Semaphore(1);

		for (int i = 0; i < 20; i++) {
			saldo = Double.parseDouble(df.format(Math.random() * 10000).replace(',', '.'));
			valor  = Double.parseDouble(df.format(Math.random() * 10000).replace(',', '.'));
			cod = (int) (Math.random() * 100);
			Thread transacao = new ThreadBanco(cod, saldo, valor, semaforo, semaforo2);
			transacao.start();
		}

	}

}
