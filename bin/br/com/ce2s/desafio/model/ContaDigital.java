package br.com.ce2s.desafio.model;

public class ContaDigital {
	
	private String cpf_cliente;
	private String agencia;
	private String conta;
	private double saldo;
	private String bloqueado;
	public ContaDigital(String cpf_cliente, String agencia, String conta, double saldo, String bloqueado) {
		super();
		this.cpf_cliente = cpf_cliente;
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
		this.bloqueado = bloqueado;
	}
	public String getCpfCliente() {
		return cpf_cliente;
	}
	public void setCpfCliente(String cpf) {
		this.cpf_cliente = cpf;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String ag) {
		this.agencia = ag;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	

}
