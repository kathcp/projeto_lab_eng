package edu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.dao.DatabaseConnection;
import edu.modelo.Veiculo;
import servicos.EnumStatusVeiculo;


public class DaoVeiculo implements IDaoVeiculo {

	@Override
	public void adicionar(Veiculo v) {
		try {
			Connection con = DatabaseConnection.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO tb_veiculo (placa, chassi, marca, modelo, ano_fab, ano_mod, carroceria, ");
			sb.append("cap_carga, km_troca_oleo, ano_exerc_licenc, dt_documento, status )");
			sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setString(1, v.getPlaca());
			st.setString(2, v.getChassi());
			st.setString(3, v.getMarca());
			st.setString(4, v.getModelo());
			st.setDate(5, new Date(v.getAnoFabricacao().getTime()));
			st.setDate(6, new Date(v.getAnoModelo().getTime()));
			st.setString(7, v.getCarroceria());
			st.setInt(8, v.getCapacidadeCarga());
			st.setInt(9, v.getKmTrocaOleo());
			st.setDate(10, new Date(v.getExerLicenciamento().getTime()));
			st.setDate(11, new Date(v.getDataDocumento().getTime()));
			st.setString(12, v.getStatus());

			st.executeUpdate();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Veiculo v) {
		{
			try {
				Connection con = DatabaseConnection.getConnection();

				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE tb_veiculo SET chassi = ?, marca = ?, modelo = ?, ano_fab = ?, ano_mod = ?, carroceria = ?, ");
				sb.append("cap_carga = ?, km_troca_oleo = ?, ano_exerc_licenc = ?, dt_documento = ?, status = ? ");
				sb.append("WHERE placa = ?");

				PreparedStatement st = con.prepareStatement(sb.toString());
				st.setString(1, v.getChassi());
				st.setString(2, v.getMarca());
				st.setString(3, v.getModelo());
				st.setDate(4, new Date(v.getAnoFabricacao().getTime()));
				st.setDate(5, new Date(v.getAnoModelo().getTime()));
				st.setString(6, v.getCarroceria());
				st.setInt(7, v.getCapacidadeCarga());
				st.setInt(8, v.getKmTrocaOleo());
				st.setDate(9, new Date(v.getExerLicenciamento().getTime()));
				st.setDate(10, new Date(v.getDataDocumento().getTime()));
				st.setString(11, v.getStatus());
				st.setString(12, v.getPlaca());

				st.executeUpdate();
				con.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void remover(Veiculo v) {
		try {
			Connection con = DatabaseConnection.getConnection();
			String sql = "DELETE FROM tb_veiculo WHERE placa = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, v.getPlaca());
			st.executeUpdate();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Veiculo> listarPorPlaca(String placa) {
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		try {
			Connection con = DatabaseConnection.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT placa, chassi, marca, modelo, ano_fab, ano_mod, carroceria, ");
			sb.append("cap_carga, km_troca_oleo, ano_exerc_licenc, dt_documento, status FROM tb_veiculo WHERE placa like ?");

			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setString(1, "%" + placa + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Veiculo v = new Veiculo();
				v.setPlaca(rs.getString("placa"));
				v.setChassi(rs.getString("chassi"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAnoFabricacao(rs.getDate("ano_fab"));
				v.setAnoModelo(rs.getDate("ano_mod"));
				v.setCarroceria(rs.getString("carroceria"));
				v.setCapacidadeCarga(rs.getInt("cap_carga"));
				v.setKmTrocaOleo(rs.getInt("km_troca_oleo"));
				v.setExerLicenciamento(rs.getDate("ano_exerc_licenc"));
				v.setDataDocumento(rs.getDate("dt_documento"));
				v.setStatus(rs.getString("status"));
				listaVeiculos.add(v);

			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listaVeiculos;

	}

	@Override
	public List<Veiculo> listarPorStatus(String t) {
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		try {
			Connection con = DatabaseConnection.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT placa, chassi, marca, modelo, ano_fab, ano_mod, carroceria, ");
			sb.append("cap_carga, km_troca_oleo, ano_exerc_licenc, dt_documento, status FROM tb_veiculo WHERE status = ?");

			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setString(1, t);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Veiculo v = new Veiculo();
				v.setPlaca(rs.getString("placa"));
				v.setChassi(rs.getString("chassi"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAnoFabricacao(rs.getDate("ano_fab"));
				v.setAnoModelo(rs.getDate("ano_mod"));
				v.setCarroceria(rs.getString("carroceria"));
				v.setCapacidadeCarga(rs.getInt("cap_carga"));
				v.setKmTrocaOleo(rs.getInt("km_troca_oleo"));
				v.setExerLicenciamento(rs.getDate("ano_exerc_licenc"));
				v.setDataDocumento(rs.getDate("dt_documento"));
				v.setStatus(rs.getString("status"));
				listaVeiculos.add(v);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listaVeiculos;
	}

	public List<Veiculo> listar() {

		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		try {
			Connection con = DatabaseConnection.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT placa, chassi, marca, modelo, ano_fab, ano_mod, carroceria, ");
			sb.append("cap_carga, km_troca_oleo, ano_exerc_licenc, dt_documento, status FROM tb_veiculo");

			PreparedStatement st = con.prepareStatement(sb.toString());

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Veiculo v = new Veiculo();
				v.setPlaca(rs.getString("placa"));
				v.setChassi(rs.getString("chassi"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAnoFabricacao(rs.getDate("ano_fab"));
				v.setAnoModelo(rs.getDate("ano_mod"));
				v.setCarroceria(rs.getString("carroceria"));
				v.setCapacidadeCarga(rs.getInt("cap_carga"));
				v.setKmTrocaOleo(rs.getInt("km_troca_oleo"));
				v.setExerLicenciamento(rs.getDate("ano_exerc_licenc"));
				v.setDataDocumento(rs.getDate("dt_documento"));
				v.setStatus(rs.getString("status"));
				listaVeiculos.add(v);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listaVeiculos;
	}

	@Override
	public List<String> listarPlacasAtivas() {
		List<String> listaVeiculos = new ArrayList<>();
		;
		try {
			Connection con = DatabaseConnection.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT placa ");
			sb.append("FROM tb_veiculo WHERE status != ?");

			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setString(1, EnumStatusVeiculo.VENDIDO.toString());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				listaVeiculos.add(rs.getString("placa"));
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return listaVeiculos;
	}

}
