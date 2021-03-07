package rodarte.testern.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelcliente.Cliente;

public class DAO {

	public DAO() {
		// TODO Auto-generated constructor stub
	}

	// Método para salvar os clientes no banco de dados
	public void salvarCliente(Cliente cli) throws Exception {

		// Abertura de conexão com o banco
		Connection conn = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
		PreparedStatement pstm = null;

		try {

			// Instruções para inserção de dados no banco
			String sql = "INSERT INTO Cliente (idCliente, nome, sexo, data_nascimento, nota1, nota2, nota3) values (?, ?, ?, ?, ?, ?, ?);";
			pstm = conn.prepareStatement(sql);

			pstm.setLong(1, cli.getIdentificacao());
			pstm.setString(2, cli.getNome());
			pstm.setString(3, cli.getSexo());
			pstm.setDate(4, new java.sql.Date(cli.getDataNascimento().getTime()));
			pstm.setInt(5, cli.getNota1());
			pstm.setInt(6, cli.getNota2());
			pstm.setInt(7, cli.getNota3());
			pstm.execute();

			System.out.println("Cliente Salvo");

		} catch (SQLException ex) {
			// Em caso de erro
			System.out.println("Erro ao Salvar Cliente: " + ex);
		} finally {
			// fechar conexão com o banco
			BancoConexao.fechar(conn, pstm, null);
		}

	}

	// Método que busca os clientes pelo nome no banco de dados
	public List<Cliente> listarNome() throws Exception {

		// Lista para armazenar os dados do db
		List<Cliente> listaClientes = new ArrayList<Cliente>();

		// Abertura de conexão com o banco
		Connection conn = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM Cliente ORDER BY nome;";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				Cliente cli = new Cliente();
				cli.setIdentificacao(rs.getLong(1));
				cli.setNome(rs.getString(2));
				cli.setSexo(rs.getString(3));
				cli.setDataNascimento(rs.getDate(4));
				cli.setNota1(rs.getInt(5));
				cli.setNota2(rs.getInt(6));
				cli.setNota3(rs.getInt(7));

				listaClientes.add(cli);
			}

		} catch (Exception ex) {
			System.out.println("Erro ao Listar Cliente: " + ex);
		} finally {
			BancoConexao.fechar(conn, pstm, rs);
		}

		return listaClientes;
	}

	// Método que busca os clientes por idade no banco de dados
	public List<Cliente> listarIdade() throws Exception {

		// Lista para armazenar os dados do db (database)
		List<Cliente> listaClientes = new ArrayList<Cliente>();

		// Abertura de conexão com o banco
		Connection conn = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// Instrução do banco de dados obtendo os clientes por idade
			String sql = "SELECT * FROM Cliente ORDER BY data_nascimento;";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				// Recuperação dos dados
				Cliente cli = new Cliente();
				cli.setIdentificacao(rs.getLong(1));
				cli.setNome(rs.getString(2));
				cli.setSexo(rs.getString(3));
				cli.setDataNascimento(rs.getDate(4));
				cli.setNota1(rs.getInt(5));
				cli.setNota2(rs.getInt(6));
				cli.setNota3(rs.getInt(7));

				listaClientes.add(cli);
			}

		} catch (Exception ex) {
			// Em caso de erro
			System.out.println("Erro ao Listar Cliente por idade: " + ex);
		} finally {
			// Incerramento do banco de dados
			BancoConexao.fechar(conn, pstm, rs);
		}

		return listaClientes;
	}

	// Método que busca os clientes do sexo masculino no banco de dados
	public List<Cliente> listarMasculino() throws Exception {

		// Lista para armazenar os dados dos clientes masculinos
		List<Cliente> listaClientes = new ArrayList<Cliente>();

		// Abertura de conexão com o banco
		Connection conn = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// Instrução do banco de dados obtendo os clientes por sexo masculino
			String sql = "SELECT * FROM Cliente WHERE Cliente.sexo = 'M';";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				// Recuperação dos dados
				Cliente cli = new Cliente();
				cli.setIdentificacao(rs.getLong(1));
				cli.setNome(rs.getString(2));
				cli.setSexo(rs.getString(3));
				cli.setDataNascimento(rs.getDate(4));
				cli.setNota1(rs.getInt(5));
				cli.setNota2(rs.getInt(6));
				cli.setNota3(rs.getInt(7));

				listaClientes.add(cli);
			}

		} catch (Exception ex) {
			// Em caso de erro
			System.out.println("Erro ao listar cliente do sexo masculino: " + ex);
		} finally {
			// Incerramento do banco de dados
			BancoConexao.fechar(conn, pstm, rs);
		}

		return listaClientes;
	}

	// Método que busca os clientes do sexo feminino no banco de dados
	public List<Cliente> listarFeminino() throws Exception {

		// Lista para armazenar os dados do db (database)
		List<Cliente> listaClientes = new ArrayList<Cliente>();

		// Abertura de conexão com o banco
		Connection conn = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// Instrução do banco de dados obtendo os clientes por idade
			String sql = "SELECT * FROM Cliente WHERE Cliente.sexo = 'F';";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				// Recuperação dos dados
				Cliente cli = new Cliente();
				cli.setIdentificacao(rs.getLong(1));
				cli.setNome(rs.getString(2));
				cli.setSexo(rs.getString(3));
				cli.setDataNascimento(rs.getDate(4));
				cli.setNota1(rs.getInt(5));
				cli.setNota2(rs.getInt(6));
				cli.setNota3(rs.getInt(7));

				listaClientes.add(cli);
			}

		} catch (Exception ex) {
			// Em caso de erro
			System.out.println("Erro ao listar cliente por sexo feminino: " + ex);
		} finally {
			// Incerramento do banco de dados
			BancoConexao.fechar(conn, pstm, rs);
		}

		return listaClientes;
	}
}
