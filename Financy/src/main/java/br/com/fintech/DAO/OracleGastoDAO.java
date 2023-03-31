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
import br.com.fintech.entity.Gasto;

public class OracleGastoDAO implements GastoDAO {

	@Override
	public void cadastrar(Gasto gasto) throws DBException {
		
		
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
			
			if (lsSeq.contains("SEQ_USER_" + String.valueOf(gasto.getCdUser())) == false){
				String sql1 = ("CREATE SEQUENCE seq_user_"+ String.valueOf(gasto.getCdUser()) +" INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 2000 CYCLE ORDER");
				stmt1 = connection.prepareStatement(sql1);
				stmt1.executeUpdate();	
			}
				
		String sql2 = "INSERT INTO T_SCF_GASTO (cd_gasto, cd_user, vl_gasto, dt_gasto, cat_gasto,  ds_gasto) VALUES (SEQ_USER_"
				+ String.valueOf(gasto.getCdUser()) + ".NEXTVAL,?,?,?,?,?)";
		
		stmt2 = connection.prepareStatement(sql2);
		stmt2.setInt(1, gasto.getCdUser());
		stmt2.setDouble(2, gasto.getValor());
		stmt2.setDate(3, gasto.getData());
		stmt2.setString(4, gasto.getCategoriaGasto());
		stmt2.setString(5, gasto.getDescricao());
		
		stmt2.executeUpdate();
		System.out.println("Cadastro de Gasto concluído com sucesso.");
		
		} catch (SQLException e) {
			System.out.println("Cadastro de Gasto Falhou.");
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
	public void atualizar(Gasto gasto) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE T_SCF_GASTO SET vl_gasto = ?, dt_gasto = ?, cat_gasto = ?, ds_gasto = ? WHERE cd_gasto = ? AND cd_user = ? ";
			stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, gasto.getValor());
			stmt.setDate(2, gasto.getData());
			stmt.setString(3, gasto.getCategoriaGasto());
			stmt.setString(4, gasto.getDescricao());
			stmt.setInt(5, gasto.getCdGasto());
			stmt.setInt(6, gasto.getCdUser());
			
			System.out.println(gasto.getValor());
			System.out.println(gasto.getData());
			System.out.println(gasto.getCategoriaGasto());
			System.out.println(gasto.getDescricao());
			System.out.println(gasto.getCdGasto());
			System.out.println(gasto.getCdUser());
			
			
			stmt.executeUpdate();
			System.out.println("Atualização de Gasto concluído com sucesso.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Atualização de Gasto falhou.");
			
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
	public void remover(int cdGasto, int cdUser) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "DELETE FROM T_SCF_GASTO WHERE cd_gasto = ? and cd_user = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdGasto);
			stmt.setInt(2, cdUser);
			
			stmt.executeUpdate();
			System.out.println("Remoção de Gasto concluída com sucesso.");
			
		}catch(SQLException e){
			
			e.printStackTrace();
			System.out.println("Remoção de Gasto falhou.");
			
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
	public List<Gasto> listar(int cdUser) {
		
		List<Gasto> lista = new ArrayList<>();
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Gasto gasto = null;
		
		try {
			
			String sql = "SELECT* FROM T_SCF_GASTO WHERE cd_user = ? ORDER BY dt_gasto DESC";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int cdGasto = rs.getInt("cd_gasto");
				double vlGasto = rs.getDouble("vl_gasto");
				Date dtGasto = rs.getDate("dt_gasto");
				String catGasto = rs.getString("cat_gasto");
				String dsGasto = rs.getString("ds_gasto");
				
				gasto = new Gasto(cdGasto, cdUser, vlGasto, dtGasto, catGasto, dsGasto);
				lista.add(gasto);
				
			}
			
			System.out.println("Listagem de Gasto concluída com sucesso.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Listagem de Gasto falhou.");
			
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
	public Gasto buscar(int cdGasto1, int cdUser) {
			
			Connection connection = DBConnection.getInstance().tryConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Gasto gasto = null;
			
			try {

				stmt = connection.prepareStatement("SELECT* FROM T_SCF_GASTO WHERE cd_gasto = ? and cd_user = ?");
				stmt.setInt(1, cdGasto1);
				stmt.setInt(2, cdUser);
				rs = stmt.executeQuery();

				if (rs.next()){
					
					int cdGasto = rs.getInt("cd_gasto");
					double vlGasto = rs.getDouble("vl_gasto");
					Date dtGasto = rs.getDate("dt_gasto");
					String catGasto = rs.getString("cat_gasto");
					String dsGasto = rs.getString("ds_gasto");
					
					System.out.println(catGasto);
					System.out.println(dsGasto);
					
					gasto = new Gasto(cdGasto, cdUser, vlGasto, dtGasto, dsGasto, catGasto);
					System.out.println(gasto.getCategoriaGasto());
					System.out.println(gasto.getDescricao());

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
			return gasto;
		
		
	}

}
