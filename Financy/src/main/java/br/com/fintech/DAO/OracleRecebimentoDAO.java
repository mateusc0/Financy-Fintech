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
import br.com.fintech.entity.Recebimento;

public class OracleRecebimentoDAO implements RecebimentoDAO {

	@Override
	public void cadastrar(Recebimento recebimento) throws DBException {
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
			
			if (lsSeq.contains("SEQ_USER_" + String.valueOf(recebimento.getCdUser())) == false){
				String sql1 = ("CREATE SEQUENCE seq_user_"+ String.valueOf(recebimento.getCdUser()) +" INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 2000 CYCLE ORDER");
				stmt1 = connection.prepareStatement(sql1);
				stmt1.executeUpdate();	
			}
			
		String sql2 = "INSERT INTO T_SCF_RECEBIMENTO (cd_recebmnt, cd_user, vl_recebmnt, dt_recebmnt, ds_recebmnt) VALUES (SEQ_USER_"
				+ String.valueOf(recebimento.getCdUser()) + ".NEXTVAL,?,?,?,?)";
		stmt2 = connection.prepareStatement(sql2);
		stmt2.setInt(1, recebimento.getCdUser());
		stmt2.setDouble(2, recebimento.getValor());
		stmt2.setDate(3, recebimento.getData());
		stmt2.setString(4, recebimento.getDescricao());
		
		stmt2.executeUpdate();
		System.out.println("Cadastro de Recebimento concluído com sucesso.");
		} catch (SQLException e) {
			System.out.println("Cadastro de Recebimento Falhou.");
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
	public void atualizar(Recebimento recebimento) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE T_SCF_RECEBIMENTO SET vl_recebmnt = ?, dt_recebmnt = ?, ds_recebmnt = ? WHERE cd_recebmnt = ? AND cd_user = ? ";
			stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, recebimento.getValor());
			stmt.setDate(2, recebimento.getData());
			stmt.setString(3, recebimento.getDescricao());
			stmt.setInt(4, recebimento.getCdRecebimento());
			stmt.setInt(5, recebimento.getCdUser());
			
			stmt.executeUpdate();
			System.out.println("Atualização de Recebimento concluído com sucesso.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Atualização de Recebimento falhou.");
			
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
	public void remover(int cdRecebimento, int cdUser) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "DELETE FROM T_SCF_RECEBIMENTO WHERE cd_recebmnt = ? and cd_user = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdRecebimento);
			stmt.setInt(2, cdUser);
			
			stmt.executeUpdate();
			System.out.println("Remoção de Recebimento concluída com sucesso.");
			
		}catch(SQLException e){
			
			e.printStackTrace();
			System.out.println("Remoção de Recebimento falhou.");
			
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
	public List<Recebimento> listar(int cdUser) {
		List<Recebimento> lista = new ArrayList<>();
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Recebimento recebimento = null;
		
		try {
			
			String sql = "SELECT* FROM T_SCF_RECEBIMENTO WHERE cd_user = ? ORDER BY dt_recebmnt DESC";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int cdRecebimento = rs.getInt("cd_recebmnt");
				double vlRecebimento = rs.getDouble("vl_recebmnt");
				Date dtRecebimento = rs.getDate("dt_recebmnt");
				String dsRecebimento = rs.getString("ds_recebmnt");
				
				recebimento = new Recebimento(cdRecebimento, cdUser, vlRecebimento, dtRecebimento, dsRecebimento);
				lista.add(recebimento);
			
			}

			System.out.println("Listagem de Recebimento concluída com sucesso.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Listagem de Recebimento falhou.");
			
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
	public Recebimento buscar(int cdRecebimento1, int cdUser) {
		
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Recebimento recebimento = null;
		
		try {

			stmt = connection.prepareStatement("SELECT* FROM T_SCF_RECEBIMENTO WHERE cd_recebmnt = ? and cd_user = ?");
			stmt.setInt(1, cdRecebimento1);
			stmt.setInt(2, cdUser);
			rs = stmt.executeQuery();

			if (rs.next()){
				
				int cdRecebimento = rs.getInt("cd_recebmnt");
				double vlRecebimento = rs.getDouble("vl_recebmnt");
				Date dtRecebimento = rs.getDate("dt_recebmnt");
				String dsRecebimento = rs.getString("ds_recebmnt");
				
				recebimento = new Recebimento(cdRecebimento, cdUser, vlRecebimento, dtRecebimento, dsRecebimento);

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
		return recebimento;
	}

}
