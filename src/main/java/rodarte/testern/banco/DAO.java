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

	// Método para salvar os clientes no db
	public void salvarCliente(Cliente cli) throws Exception {

		Connection conn = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
		PreparedStatement pstm = null;

		try {

			String sql = "INSERT INTO Cliente (idCliente, nome, sexo, data_nascimento, nota1, nota2, nota3) values (?, ?, ?, ?, ?, ?, ?);";
			pstm = conn.prepareStatement(sql);

			pstm.setLong(1, cli.getIdentificacao());
			pstm.setString(2, cli.getNome());
			pstm.setString(3, cli.getSexo());
			pstm.setDate(4, cli.getDataNascimento());
			pstm.setInt(5, cli.getNota1());
			pstm.setInt(6, cli.getNota2());
			pstm.setInt(7, cli.getNota3());
			pstm.execute();

			System.out.println("Cliente Salvo");

		} catch (SQLException ex) {
			System.out.println("Erro ao Salvar Cliente: " + ex);
		} finally {
			BancoConexao.fechar(conn, pstm, null);
		}

	}

	// Método para listar os clientes do db
	public List<Cliente> listar() throws Exception {

		List<Cliente> listaClientes = new ArrayList<Cliente>();

		Connection conn = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM Cliente ORDER BY nome;";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				Cliente cli = new Cliente();
				cli.setIdentificacao(rs.getLong(0));
				cli.setNome(rs.getString(1));
				cli.setSexo(rs.getString(rs.getString(2)));
				cli.setDataNascimento(rs.getDate(3));
				cli.setNota1(rs.getInt(4));
				cli.setNota2(rs.getInt(5));
				cli.setNota3(rs.getInt(6));

				listaClientes.add(cli);
			}

		} catch (Exception ex) {
			System.out.println("Erro ao Listar Cliente: " + ex);
		} finally {
			BancoConexao.fechar(conn, pstm, rs);
		}

		return listaClientes;
	}
}
