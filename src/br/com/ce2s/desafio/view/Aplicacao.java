package br.com.ce2s.desafio.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.ce2s.desafio.model.Cliente;
import br.com.ce2s.desafio.model.Conexao;
import br.com.ce2s.desafio.model.ContaDigital;
import br.com.ce2s.desafio.model.Transacao;
import br.com.ce2s.desafio.model.ValidaCPF;

public class Aplicacao {

	public static void main(String[] args) throws SQLException {
		
		Connection conexao = Conexao.getConexao();
		Scanner entrada = new Scanner(System.in);

		Transacao.listaClientes(conexao);

		//Transacao.insereCliente(conexao, entrada);
		
		//Transacao.insereConta(conexao, entrada);
		
		//Transacao.listaClientes(conexao);
		
		//Transacao.listaContas(conexao);
		
		//Transacao.extratoConta(conexao);
		
		Transacao.depositar(conexao, entrada);
		
		//Transacao.debitar(conexao, entrada);	
		
		conexao.close();
		entrada.close();
		
	}
}	
	
