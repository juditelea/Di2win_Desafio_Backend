package br.com.ce2s.desafio.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Transacao {
	
	public static void listaClientes(Connection conexao) throws SQLException {
		
		
		String sql = "SELECT * FROM cliente";
		
		List<Cliente> clientes = new ArrayList<>();
		Statement stmt = conexao.createStatement();
		ResultSet resultado = stmt.executeQuery(sql);
		
		while (resultado.next()) {
			int id = resultado.getInt("id");
			String nome = resultado.getString("nome");
			String cpf = resultado.getString("cpf");
			String dataNascimento = resultado.getString("dataNascimento");
			clientes.add(new Cliente(id, nome, cpf, dataNascimento));
		}
		
		for (Cliente c : clientes) {
			System.out.println(c.getId() + " ==> " + c.getNome() + " - " + c.getCpf() + " - " + c.getDataNascimento());
		}
	}
	
	public static void insereCliente(Connection conexao, Scanner entrada) throws SQLException {
		
		boolean sair =true;
		
		while(sair) {
			
			System.out.println("Insira os dados do cliente:");
			System.out.print("Digite o Nome: ");
			String nome_cliente = entrada.nextLine();
			System.out.print("Digite o CPF (só numeros): ");
			String cpf_cliente = entrada.nextLine();
			System.out.print("Data de Nascimento: ");
			String dn_cliente = entrada.nextLine();
			if (ValidaCPF.isCPF(cpf_cliente) == false) {
				System.out.printf("Erro, CPF invalido !!!\n");
			} else if (cpfExiste(conexao, cpf_cliente)) {
				System.out.println("Este cliente já existe!!");
			} else {
				
				String sql = "INSERT INTO cliente (nome, cpf, dataNascimento) VALUES (?, ?, ?)";
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setString(1, nome_cliente);
				stmt.setString(2, cpf_cliente);
				stmt.setString(3, dn_cliente);
				stmt.execute();
			}
			System.out.print("Deseja incluir novo cliente? ");
			if ("n".equalsIgnoreCase(entrada.nextLine())) {
				return;
			}
		}
		
	}
	
	public static void insereConta(Connection conexao, Scanner entrada) throws SQLException {
		
		boolean sair =true;
		
		while(sair) {
			
			System.out.println("Insira os dados da Conta:");
			System.out.print("Digite o CPF (só numeros): ");
			String cpf_conta = entrada.nextLine();
			System.out.print("Agencia: ");
			String ag = entrada.nextLine();
			System.out.print("Conta: ");
			String conta = entrada.nextLine();
			System.out.print("Saldo: ");
			double saldo = entrada.nextDouble();
			String bloqueado = "0";
			if (ValidaCPF.isCPF(cpf_conta) == false) {
				System.out.printf("Erro, CPF invalido !!!\n");
			} else if (contaExiste(conexao, cpf_conta)){
				System.out.println("Esta conta já existe!");
			} else {
				
				String sql = "INSERT INTO conta_digital (cpf_cliente, agencia, conta, saldo, bloqueado) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setString(1, cpf_conta);
				stmt.setString(2, ag);
				stmt.setString(3, conta);
				stmt.setDouble(4, saldo);
				stmt.setString(5, bloqueado);
				stmt.execute();
			}
			System.out.print("Deseja incluir nova conta? ");
			if ("n".equalsIgnoreCase(entrada.nextLine())) {
				return;
			}
			
		}
		
	}
	
	public static void extratoConta(Connection conexao, String cpf, Date data_inicial, Date data_final) throws SQLException {
		
		if (contaBloqueada(conexao, cpf)) {
			System.out.println("A conta está bloqueada!!");
			return;
		} else {
		
			String sql = "SELECT c.nome, m.data_movimentacao, m.valor, m.saldo_anterior, "
					+ "m.saldo_atual, m.debito_credito "
					+ "FROM conta_digital cd "
					+ "INNER JOIN movimentacao m on cd.cpf_cliente = m.cpf_cliente "
					+ "INNER JOIN cliente c on c.cpf = cd.cpf_cliente "
					+ "WHERE cd.cpf_cliente = ? AND cd.bloqueado = '0' "
					+ "AND m.data_movimentacao >= ? and m.data_movimentacao <= ? ";
		
			
			List<Movimentacao> extrato = new ArrayList<>();
	
			PreparedStatement stmt =  conexao.prepareStatement(sql);
			
			Date data_init = (Date) data_inicial;
			java.sql.Timestamp sqlDate_init  = new java.sql.Timestamp(data_init.getTime());
			Date data_fim = (Date) data_final;
			java.sql.Timestamp sqlDate_fim  = new java.sql.Timestamp(data_fim.getTime());
			
			
			stmt.setString(1, cpf);
			stmt.setTimestamp(2, sqlDate_init);
			stmt.setTimestamp(3, sqlDate_fim);
			ResultSet resultado = stmt.executeQuery();
			
			while (resultado.next()) {
				Date data_movimentacao = resultado.getTimestamp("data_movimentacao");
				double valor = resultado.getDouble("valor");
				double saldo_anterior = resultado.getDouble("saldo_anterior");
				double saldo_atual = resultado.getDouble("saldo_atual");
				String debito_credito = resultado.getNString("debito_credito");
				
				extrato.add(new Movimentacao(cpf, data_movimentacao, valor, 
						saldo_anterior, saldo_atual, debito_credito));
			}
			
			System.out.println("Nome      Data Movimentacao    Valor  Saldo_Anterior  Saldo_Atual  C/D\n");
			
			for (Movimentacao m : extrato) {
				String nome = consultaNomeCliente(conexao, m.getCpf_cliente());
				
				System.out.println(nome + "       " +  m.getData_movimentacao() + "  " + m.getValor()
				+ "  " + m.getSaldo_anterior() + "  " + m.getSaldo_atual() + "  " +  m.getDebito_credito());
			}
		} 
	}
	
	public static void depositar(Connection conexao, Scanner entrada) throws SQLException {
		
		boolean sair =true;
		
		while(sair) {
			
			System.out.println("Depositar:");
			System.out.print("Digite o CPF (só numeros): ");
			String cpf_mov = entrada.nextLine();
			System.out.print("Valor: ");
			double valor = entrada.nextDouble();
			if (ValidaCPF.isCPF(cpf_mov) == false) {
				System.out.printf("Erro, CPF invalido !!!\n");
				return;
			} else if (!contaExiste(conexao, cpf_mov)){
				System.out.println("Este CPF não existe na conta!!");
				return;
			} else {
				double saldo_anterior = consultaSaldo(conexao, cpf_mov);
				double saldo_atual = saldo_anterior + valor;
				if (valor > 2000) {
					valor = 2000;
					System.out.println("Seu saque não pode ser maior que R$2000,00!!");
				}
					
				atualizaSaldo(conexao, saldo_atual, cpf_mov);
				inclueMovimentacao(conexao, cpf_mov, valor, saldo_anterior, saldo_atual, "C");
				
				
				System.out.println("Depósito executado!!");
			}
			System.out.print("Deseja Depositar em outra conta? ");
			if ("n".equalsIgnoreCase(entrada.nextLine())) {
				return;
			}
		}
		
	}
	
	public static void sacar(Connection conexao, Scanner entrada) throws SQLException {
		
		boolean sair =true;
		
		while(sair) {
			System.out.println("Debitar:");
			System.out.print("Digite o CPF (só numeros): ");
			String cpf_mov = entrada.nextLine();
			System.out.print("Digite o Valor: ");
			double valor = entrada.nextDouble();
			if (ValidaCPF.isCPF(cpf_mov) == false) {
				System.out.printf("Erro, CPF invalido !!!\n");
				return;
			} else if (!contaExiste(conexao, cpf_mov)){
				System.out.println("Este CPF não existe na conta!!");
				return;
			} else {
				double saldo_anterior = consultaSaldo(conexao, cpf_mov);
				double saldo_atual = saldo_anterior - valor;
				if (saldo_atual < 0) {
					saldo_atual = 0;
					System.out.println("O saldo da sua conta não pode ser negativo!!");
				}
					
				atualizaSaldo(conexao, saldo_atual, cpf_mov);
				inclueMovimentacao(conexao, cpf_mov, valor, saldo_anterior, saldo_atual, "D");
				System.out.println("Saque executado!!");
			}
			System.out.print("Sacar de outra conta? (S/n): ");
			if ("n".equalsIgnoreCase(entrada.nextLine())) {
				return;
			}
		}
		
	}
	
	private static void atualizaSaldo(Connection conexao, double saldo, String cpf) throws SQLException {
		PreparedStatement st = conexao
				.prepareStatement("UPDATE conta_digital SET saldo = ? WHERE cpf_cliente = ?");
		st.setDouble(1, saldo);
		st.setString(2, cpf);
		st.execute();
	}
	
	private static void inclueMovimentacao(Connection conexao,  String cpf, double valor, 
			double saldo_ant, double saldo_atu, String debito_credito) throws SQLException {
		
		Date data = new Date();
		java.sql.Timestamp sqlDate  = new java.sql.Timestamp(data.getTime());
		System.out.println(sqlDate);
		
		String sql = "INSERT INTO movimentacao (cpf_cliente, data_movimentacao, valor"
				+ ", saldo_anterior, saldo_atual, debito_credito)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, cpf);
        stmt.setTimestamp(2, sqlDate);
        stmt.setDouble(3, valor);
        stmt.setDouble(4, saldo_ant);
		stmt.setDouble(5, saldo_atu);
		stmt.setString(6, debito_credito);
		stmt.execute();

	}
	
	public static double consultaSaldo(Connection conexao, String cpf) throws SQLException {
		
		String sql = "SELECT saldo FROM conta_digital WHERE cpf_cliente = ?" ;
		
		PreparedStatement stmt =  conexao.prepareStatement(sql);
		stmt.setString(1, cpf);
		ResultSet resultado = stmt.executeQuery();
		if (resultado.next()) {
			return resultado.getDouble("saldo");
		} else {
			return 0;
		}
		
		
	}
	
	public static void listaContas(Connection conexao) throws SQLException {	
		
		String sql = "SELECT cd.agencia as agencia, cd.conta as conta, "
				+ "cd.saldo as saldo, cd.cpf_cliente as cpf, cd.bloqueado as bloqueado"
				+ " FROM cliente c, conta_digital cd WHERE c.cpf = cd.cpf_cliente";
	
		
		List<ContaDigital> contas = new ArrayList<>();
		
		Statement stmt = conexao.createStatement();
		ResultSet resultado = stmt.executeQuery(sql);
		
		while (resultado.next()) {
			String cpf_cliente = resultado.getString("cpf");
			String agencia = resultado.getString("agencia");
			String conta = resultado.getString("conta");
			double saldo = resultado.getDouble("saldo");
			String bloqueado = resultado.getString("bloqueado");
			
			contas.add(new ContaDigital(cpf_cliente, agencia, conta, saldo, bloqueado));
		}
		System.out.println();
		for (ContaDigital c : contas) {
			String nome = consultaNomeCliente(conexao, c.getCpfCliente());
			String bl = c.getBloqueado();
			bl = "0".equals(bl) ? "Desbloqueada" : "Bloqueada";
			System.out.println(nome +  " ==> Da agencia: " + c.getAgencia()  
			+ " e conta: " + c.getConta() + " e saldo de R$" + c.getSaldo() + ": Conta " + bl);
		}
	}
	
	public static String consultaNomeCliente(Connection conexao, String cpf) throws SQLException {
	
		String sql = "SELECT nome FROM cliente WHERE cpf = ?" ;
		
		PreparedStatement stmt =  conexao.prepareStatement(sql);
		stmt.setString(1, cpf);
		ResultSet resultado = stmt.executeQuery();	
		if (resultado.next()) {
			return resultado.getString("nome");
		} else {
			return "";
		}
	
	}
	
	public static boolean cpfExiste(Connection conexao, String cpf) throws SQLException {
		
		String sql = "SELECT cpf FROM cliente WHERE cpf = ?" ;
		
		PreparedStatement stmt =  conexao.prepareStatement(sql);
		stmt.setString(1, cpf);
		ResultSet resultado = stmt.executeQuery();
		return resultado.next();
		
	}

	public static boolean contaExiste(Connection conexao, String cpf) throws SQLException {
		
		String sql = "SELECT cpf_cliente FROM conta_digital WHERE cpf_cliente = ?" ;
		
		PreparedStatement stmt =  conexao.prepareStatement(sql);
		stmt.setString(1, cpf);
		ResultSet resultado = stmt.executeQuery();
		return resultado.next();
		
	}
	
	public static void removeCliente(Connection conexao, Scanner entrada) throws SQLException {
	
		boolean sair =true;
		
		while(sair) {
			
			System.out.print("Digite o CPF (só numeros): ");
			String cpf = entrada.nextLine();
			
			if (ValidaCPF.isCPF(cpf) == false) {
				System.out.printf("Erro, CPF invalido !!!\n");
				return;
			} else {
				
				String sql = "DELETE FROM cliente WHERE cpf = ?";
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setString(1, cpf);
				stmt.execute();
			}
			System.out.print("Deseja excluir outro cliente (S/n)? ");
			if ("n".equalsIgnoreCase(entrada.nextLine())) {
				return;
			}
		}
		
	}
	
	public static void bloqueiaConta(Connection conexao, String cpf) throws SQLException {
					
		String sql = "UPDATE conta_digital SET bloqueado = '1' WHERE cpf_cliente = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, cpf);
		stmt.execute();
		System.out.println("A conta foi bloqueada!!");
	
	}

	public static void desbloqueiaConta(Connection conexao, String cpf) throws SQLException {
	
		String sql = "UPDATE conta_digital SET bloqueado = '0' WHERE cpf_cliente = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, cpf);
		stmt.execute();
		System.out.println("A conta foi desbloqueada!!");
	}
	
	public static boolean contaBloqueada(Connection conexao, String cpf) throws SQLException {
		
		String sql = "SELECT bloqueado FROM conta_digital WHERE cpf_cliente = ?";
		String bloqueado = "";
		PreparedStatement stmt =  conexao.prepareStatement(sql);
		stmt.setString(1, cpf);
		ResultSet resultado = stmt.executeQuery();
		if (resultado.next()) {
			bloqueado = resultado.getString("bloqueado");
		}
		if( "1".equals(bloqueado)) {
			return true;
		} else {
			return false;
		}
		
		
	}

}







