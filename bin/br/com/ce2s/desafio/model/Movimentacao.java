package br.com.ce2s.desafio.model;

import java.util.Date;

public class Movimentacao {
	
	private String cpf_cliente;
	private Date data_movimentacao;
	private double valor;
	private double saldo_anterior;
	private double saldo_atual;
	private String debito_credito;
	
	public Movimentacao(String cpf_cliente, Date data_movimentacao, 
			double valor, double saldo_anterior, double saldo_atual, String debito_credito) {
		super();
		this.cpf_cliente = cpf_cliente;
		this.data_movimentacao = data_movimentacao;
		this.valor = valor;
		this.saldo_anterior = saldo_anterior;
		this.saldo_atual = saldo_atual;
		this.debito_credito = debito_credito;
	}

	public double getSaldo_anterior() {
		return saldo_anterior;
	}

	public void setSaldo_anterior(double saldo_anterior) {
		this.saldo_anterior = saldo_anterior;
	}

	public double getSaldo_atual() {
		return saldo_atual;
	}

	public void setSaldo_atual(double saldo_atual) {
		this.saldo_atual = saldo_atual;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public Date getData_movimentacao() {
		return data_movimentacao;
	}

	public void setData_movimentacao(Date data_movimentacao) {
		this.data_movimentacao = data_movimentacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDebito_credito() {
		return debito_credito;
	}

	public void setDebito_credito(String debito_credito) {
		this.debito_credito = debito_credito;
	}
	
}
