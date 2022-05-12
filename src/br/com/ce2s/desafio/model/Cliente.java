package br.com.ce2s.desafio.model;

public class Cliente {
	
	   
	private int id;
	private String nome;
	private String cpf;
	private String dataNascimento;
	
	public Cliente(int id, String nome, String cpf, String dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	/*
	 * public void verificaCpf(String cpf) {
	 * 
	 * System.out.printf("Informe um CPF: "); CPF = ler.next();
	 * 
	 * System.out.printf("\nResultado: "); // usando os metodos isCPF() e
	 * imprimeCPF() da classe "ValidaCPF" if (isCPF(cpf) == true)
	 * System.out.printf("%s\n", ValidaCPF.imprimeCPF(CPF)); else
	 * System.out.printf("Erro, CPF invalido !!!\n"); } }
	 */
	
}
