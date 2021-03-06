package edu.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.dao.DatabaseConnection;
import edu.modelo.Cliente;


public class DaoCliente implements IDaoCliente{

	public void adicionar(Cliente f) {
		try {
			Connection con = DatabaseConnection.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO tb_cliente(nome, cep, rua, numero, complemento, bairro, cidade, uf, ");
			sb.append("fone1, fone2, status, dt_cadastro, cnpj_cliente, contato ) ");
			sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setString(1, f.getNome());
			st.setString(2, f.getCep());
			st.setString(3, f.getRua());
			st.setString(4, f.getNum());
			st.setString(5, f.getCompl());
			st.setString(6, f.getBairro());
			st.setString(7, f.getCidade());
			st.setString(8, f.getUf());
			st.setString(9, f.getFone1());
			st.setString(10, f.getFone2());
			st.setString(11, f.getStatus());
			st.setDate(12, dataBanco(new Date()));
			st.setString(13, f.getCnpj());
			st.setString(14, f.getContato());

			st.executeUpdate();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(Cliente f) {
		try {
			Connection con = DatabaseConnection.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE tb_cliente SET nome = ?, cep = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, ");
			sb.append("fone1 = ?, fone2 = ?, status = ?, contato = ? ");
			sb.append("WHERE  cnpj_cliente = ?");

			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setString(1, f.getNome());
			st.setString(2, f.getCep());
			st.setString(3, f.getRua());
			st.setString(4, f.getNum());
			st.setString(5, f.getCompl());
			st.setString(6, f.getBairro());
			st.setString(7, f.getCidade());
			st.setString(8, f.getUf());
			st.setString(9, f.getFone1());
			st.setString(10, f.getFone2());
			st.setString(11, f.getStatus());
			st.setString(12, f.getContato());
			st.setString(13, f.getCnpj());
			st.executeUpdate();
			con.close();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void remover(Cliente f) {
		try {
			Connection con = DatabaseConnection.getConnection();
			String sql = "DELETE FROM tb_cliente WHERE cnpj_cliente = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, f.getCnpj());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public List<Cliente> listar() {

		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			Connection con = DatabaseConnection.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT nome, cep, rua, numero, complemento, bairro, cidade, uf, ");
			sb.append("fone1, fone2, status, dt_cadastro, cnpj_cliente, contato ");
			sb.append("FROM tb_cliente");
			PreparedStatement st = con.prepareStatement(sb.toString());

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Cliente f = new Cliente();
				f.setCnpj(rs.getString("cnpj_cliente"));
				f.setNome(rs.getString("nome"));
				f.setCep(rs.getString("cep"));
				f.setRua(rs.getString("rua"));
				f.setNum(rs.getString("numero"));
				f.setCompl(rs.getString("complemento"));
				f.setBairro(rs.getString("bairro"));
				f.setCidade(rs.getString("cidade"));
				f.setUf(rs.getString("uf"));
				f.setFone1(rs.getString("fone1"));
				f.setFone2(rs.getString("fone2"));
				f.setStatus(rs.getString("status"));
				f.setDtCadastro(rs.getDate("dt_cadastro"));
				f.setContato(rs.getString("contato"));

				lista.add(f);
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Cliente> listarPorCnpj(String t) {
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			Connection con = DatabaseConnection.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT nome, cep, rua, numero, complemento, bairro, cidade, uf, ");
			sb.append("fone1, fone2, status, dt_cadastro, cnpj_cliente, contato ");
			sb.append("FROM tb_cliente WHERE cnpj_cliente = ?");
			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setString(1, t);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Cliente f = new Cliente();
				f.setCnpj(rs.getString("cnpj_cliente"));
				f.setNome(rs.getString("nome"));
				f.setCep(rs.getString("cep"));
				f.setRua(rs.getString("rua"));
				f.setNum(rs.getString("numero"));
				f.setCompl(rs.getString("complemento"));
				f.setBairro(rs.getString("bairro"));
				f.setCidade(rs.getString("cidade"));
				f.setUf(rs.getString("uf"));
				f.setFone1(rs.getString("fone1"));
				f.setFone2(rs.getString("fone2"));
				f.setStatus(rs.getString("status"));
				f.setDtCadastro(rs.getDate("dt_cadastro"));
				f.setContato(rs.getString("contato"));
				lista.add(f);
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	public List<Cliente> listarPorRazao(String t) {
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			Connection con = DatabaseConnection.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT nome, cep, rua, numero, complemento, bairro, cidade, uf, ");
			sb.append("fone1, fone2, status, dt_cadastro, cnpj_cliente, contato ");
			sb.append("FROM tb_cliente WHERE nome like ?");
			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setString(1, "%" + t + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Cliente f = new Cliente();
				f.setCnpj(rs.getString("cnpj_cliente"));
				f.setNome(rs.getString("nome"));
				f.setCep(rs.getString("cep"));
				f.setRua(rs.getString("rua"));
				f.setNum(rs.getString("numero"));
				f.setCompl(rs.getString("complemento"));
				f.setBairro(rs.getString("bairro"));
				f.setCidade(rs.getString("cidade"));
				f.setUf(rs.getString("uf"));
				f.setFone1(rs.getString("fone1"));
				f.setFone2(rs.getString("fone2"));
				f.setStatus(rs.getString("status"));
				f.setDtCadastro(rs.getDate("dt_cadastro"));
				f.setContato(rs.getString("contato"));

				lista.add(f);
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private java.sql.Date dataBanco(Date data) {
		try {
			return new java.sql.Date(data.getTime());
		} catch (Exception e) {
			return null;
		}
	}
}
