package br.com.ce2s.desafio.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public static void main(String[] args) throws SQLException, ParseException {
		
		Connection conexao = Conexao.getConexao();
		Scanner entrada = new Scanner(System.in);

		//Transacao.listaClientes(conexao);

		//Transacao.insereCliente(conexao, entrada);
		
		//Transacao.removeCliente(conexao, entrada);
		
		//Transacao.insereConta(conexao, entrada);
		
		//Transacao.listaContas(conexao);
		
		
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 Date data_inicial = formatter.parse("04/05/2022"); 
		 Date data_final = formatter.parse("17/05/2022"); 
		 Transacao.extratoConta(conexao, "11953475027", data_inicial, data_final);
		 
		
		//Transacao.depositar(conexao, entrada);
		
		//Transacao.sacar(conexao, entrada);	
		
		//Transacao.bloqueiaConta(conexao, "11953475027");
		
		conexao.close();
		entrada.close();
		
	}
}	
	
