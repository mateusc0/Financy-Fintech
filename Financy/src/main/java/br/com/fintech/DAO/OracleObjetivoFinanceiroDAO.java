package br.com.fintech.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.Exception.DBException;
import br.com.fintech.JDBC.DBConnection;
import br.com.fintech.entity.ObjetivoFinanceiro;

public class OracleObjetivoFinanceiroDAO implements ObjetivoFinanceiroDAO {

	@Override
	public void cadastrar(ObjetivoFinanceiro objetivoFinanceiro) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		Statement stmt = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		List <String> lsSeq = new ArrayList<>();
		
		try {
			
			String sql = ("SELECT sequence_name FROM user_sequences");
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				lsSeq.add(rs.getString("sequence_name"));
			}
			
			if (lsSeq.contains("SEQ_USER_" + String.valueOf(objetivoFinanceiro.getCdUser())) == false){
				String sql1 = ("CREATE SEQUENCE seq_user_"+ String.valueOf(objetivoFinanceiro.getCdUser()) +" INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 2000 CYCLE ORDER");
				stmt1 = connection.prepareStatement(sql1);
				stmt1.executeUpdate();	
			}
			
		String sql2 = "INSERT INTO T_SCF_OBJETIVO_FINANCEIRO (cd_objtv, cd_user, nm_objtv, vl_objtv, dt_objtv, ds_objtv) VALUES (SEQ_USER_"
				+ String.valueOf(objetivoFinanceiro.getCdUser()) + ".NEXTVAL,?,?,?,?,?)";
		stmt2 = connection.prepareStatement(sql2);
		stmt2.setInt(1, objetivoFinanceiro.getCdUser());
		stmt2.setString(2, objetivoFinanceiro.getNome());
		stmt2.setDouble(3, objetivoFinanceiro.getValor());
		stmt2.setDate(4, objetivoFinanceiro.getDataConclusao());
		stmt2.setString(5, objetivoFinanceiro.getDescricao());
		
		stmt2.executeUpdate();
		System.out.println("Cadastro de Objetivo Financeiro concluído com sucesso.");
		} catch (SQLException e) {
			System.out.println("Cadastro de Objetivo Financeiro Falhou.");
			e.printStackTrace();
			
		} finally{
			
			try {
				stmt.close();
				if(stmt1 != null){
				stmt1.close();
				}
				stmt2.close();
				connection.close();
				System.out.println("Connection terminated.");
			} catch(SQLException e) {
				System.out.println("Fechamento de conexão falhou.");
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void atualizar(ObjetivoFinanceiro objetivoFinanceiro) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE T_SCF_OBJETIVO_FINANCEIRO SET nm_objtv = ?, vl_objtv = ?, dt_objtv = ?, ds_objtv = ? WHERE cd_objtv = ? AND cd_user = ? ";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, objetivoFinanceiro.getNome());
			stmt.setDouble(2, objetivoFinanceiro.getValor());
			stmt.setDate(3, objetivoFinanceiro.getDataConclusao());
			stmt.setString(4, objetivoFinanceiro.getDescricao());
			stmt.setInt(5, objetivoFinanceiro.getCdObjtvFinanceiro());
			stmt.setInt(6, objetivoFinanceiro.getCdUser());
			
			stmt.executeUpdate();
			System.out.println("Atualização de Objetivo Financeiro concluído com sucesso.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Atualização de Objetivo Financeiro falhou.");
			
		}finally {
			
			try {
				stmt.close();
				connection.close();
				System.out.println("Connection terminated.");
			} catch(SQLException e) {
				System.out.println("Fechamento de conexão falhou.");
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void remover(int cdObjetivoFinanceiro, int cdUser) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "DELETE FROM T_SCF_OBJETIVO_FINANCEIRO WHERE cd_objtv = ? and cd_user = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdObjetivoFinanceiro);
			stmt.setInt(2, cdUser);
			
			stmt.executeUpdate();
			System.out.println("Remoção de Objetivo Financeiro concluída com sucesso.");
			
		}catch(SQLException e){
			
			e.printStackTrace();
			System.out.println("Remoção de Objetivo Financeiro falhou.");
			
		} finally {
		
			try {
				stmt.close();
				connection.close();
				System.out.println("Connection terminated.");
			} catch(SQLException e) {
				System.out.println("Fechamento de conexão falhou.");
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<ObjetivoFinanceiro> listar(int cdUser) {
		List<ObjetivoFinanceiro> lista = new ArrayList<>();
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ObjetivoFinanceiro objetivo = null;
		
		try {
			
			String sql = "SELECT* FROM T_SCF_OBJETIVO_FINANCEIRO WHERE cd_user = ? ORDER BY dt_objtv DESC";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int cdObjtv = rs.getInt("cd_objtv");
				String nmObjtv = rs.getString("nm_objtv");
				double vlObjtv = rs.getDouble("vl_objtv");
				Date dtObjtv = rs.getDate("dt_objtv");
				String dsObjtv = rs.getString("ds_objtv");
				
				objetivo = new ObjetivoFinanceiro(cdObjtv, cdUser, nmObjtv, vlObjtv, dtObjtv, dsObjtv);
				lista.add(objetivo);
			}
			
			System.out.println("Listagem de Objetivo Financeiro concluída com sucesso.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Listagem de Objetivo Financeiro falhou.");
			
		} finally {
			
			try {
				stmt.close();
				rs.close();
				connection.close();
				System.out.println("Connection terminated.");
			} catch(SQLException e) {
				System.out.println("Fechamento de conexão falhou.");
				e.printStackTrace();
			}	
		}
		
		
		return lista;
	}

	@Override
	public ObjetivoFinanceiro buscar(int cdObjetivoFinanceiro, int cdUser) {

		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ObjetivoFinanceiro objetivoFinanceiro = null;
		
		try {

			stmt = connection.prepareStatement("SELECT* FROM T_SCF_OBJETIVO_FINANCEIRO WHERE cd_objtv = ? and cd_user = ?");
			stmt.setInt(1, cdObjetivoFinanceiro);
			stmt.setInt(2, cdUser);
			rs = stmt.executeQuery();

			if (rs.next()){
				
				int cdObjtv = rs.getInt("cd_objtv");
				String nmObjtv = rs.getString("nm_objtv");
				double vlObjtv = rs.getDouble("vl_objtv");
				Date dtObjtv = rs.getDate("dt_objtv");
				String dsObjtv = rs.getString("ds_objtv");
				
				objetivoFinanceiro = new ObjetivoFinanceiro(cdObjtv, cdUser, nmObjtv, vlObjtv, dtObjtv, dsObjtv);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objetivoFinanceiro;
	}

}
