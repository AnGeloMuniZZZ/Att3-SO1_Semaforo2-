package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {

	private int cod;
	private double saldo;
	private double valor;
	private Semaphore semaforo;
	private Semaphore semaforo2;

	public ThreadBanco(int cod, double saldo, double valor, Semaphore semaforo, Semaphore semaforo2) {
		this.cod = cod;
		this.saldo = saldo;
		this.valor = valor;
		this.semaforo = semaforo;
		this.semaforo2 = semaforo2;
	}

	@Override
	public void run() {
		switch ((int) (Math.random() * 2)) {
		case 0:
			try {
				semaforo.acquire();
				saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			break;
		case 1:
			try {
				semaforo2.acquire();
				deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo2.release();
			}
			break;
		}
	}

	private void deposito() {
		System.out.println("Fazendo deposito de " + valor + " R$...");
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Depositado " + valor + " na conta de codigo " + cod);
		saldo = saldo + valor;
		System.out.println("Saldo atual " + saldo + " R$");
	}

	private void saque() {
		if(saldo < valor) {
			System.out.println("Saldo insuficiente, falta " + (valor - saldo) + " R$");
		} else {
			System.out.println("Preparando saque de " + valor + " R$...");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Retirar dinheiro do caixa");
			saldo = saldo - valor;
			System.out.println("Saldo atual " + saldo + " R$");
		}
	}
}
