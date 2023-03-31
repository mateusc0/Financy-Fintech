package br.com.fintech.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fintech.Exception.DBException;
import br.com.fintech.JDBC.DBConnection;
import br.com.fintech.entity.Dashboard;
import br.com.fintech.entity.Gasto;
import br.com.fintech.entity.Investimento;



public class OracleDashboardDAO implements DashboardDAO {


	private String nomeUser(int cdUser) {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String nomeUser = null;
		
		try {
			
			String sql = "SELECT nm_user FROM T_SCF_USER WHERE cd_user = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				nomeUser = rs.getString("nm_user");
				
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Busca de Nome User falhou.");
			
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
		
		
		return nomeUser;
	}


	private double totalRecebMes(int cdUser) {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		double totalRecebimento = 0;
		
		try {
			
			String sql = "SELECT SUM(vl_recebmnt)\"TOTAL_RCBMNT\" FROM T_SCF_RECEBIMENTO WHERE cd_user = ?"
					+ " AND EXTRACT(month FROM dt_recebmnt) = EXTRACT(month FROM sysdate)"
					+ "AND EXTRACT(year FROM dt_recebmnt) = EXTRACT(year FROM sysdate)";
;
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {			
				totalRecebimento = rs.getDouble("TOTAL_RCBMNT");			
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Busca de Total Recebimento falhou.");
			
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
		
		
		return totalRecebimento;
	}


	private double totalGastoMes(int cdUser) {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		double totalGasto = 0;
		
		try {
			
			String sql = "SELECT SUM(vl_gasto)\"TOTAL_GASTO\" FROM T_SCF_GASTO WHERE cd_user = ?"
					+ " AND EXTRACT(month FROM dt_gasto) = EXTRACT(month FROM sysdate)"
					+ "AND EXTRACT(year FROM dt_gasto) = EXTRACT(year FROM sysdate)";
;
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {			
				totalGasto = rs.getDouble("TOTAL_GASTO");			
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Busca de Total Gasto falhou.");
			
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
		
		
		return totalGasto;
	}


	private double totalInvestMes(int cdUser) {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		double totalInvest = 0;
		
		try {
			
			String sql = "SELECT SUM(vl_invest)\"TOTAL_INVEST\" FROM T_SCF_INVESTIMENTO WHERE cd_user = ?";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {			
				totalInvest = rs.getDouble("TOTAL_INVEST");			
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Busca de Total Investimento falhou.");
			
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
		
		
		return totalInvest;
	}


	private Gasto ultimoGasto(int cdUser) {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Gasto gasto = null;
		
		try {
			
			String sql = "SELECT* FROM T_SCF_GASTO WHERE cd_user = ? AND dt_gasto = (SELECT MAX(dt_gasto) FROM T_SCF_GASTO)";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				int cdGasto = rs.getInt("cd_gasto");
				double vlGasto = rs.getDouble("vl_gasto");
				Date dtGasto = rs.getDate("dt_gasto");
				String catGasto = rs.getString("cat_gasto");
				String dsGasto = rs.getString("ds_gasto");
				
				gasto = new Gasto(cdGasto, cdUser, vlGasto, dtGasto, catGasto, dsGasto);
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Busca de Gasto falhou.");
			
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
		
		
		return gasto;
	}


	private Investimento ultimoInvest(int cdUser) {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Investimento investimento = null;
		
		try {
			
			String sql = "SELECT* FROM T_SCF_INVESTIMENTO WHERE cd_user = ? AND dt_realizacao = (SELECT MAX(dt_realizacao) FROM T_SCF_INVESTIMENTO)";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				int cdInvest = rs.getInt("cd_invest");
				String nmInvest = rs.getString("nm_invest");
				double vlInvest = rs.getDouble("vl_invest");
				Date dtRealInvest = rs.getDate("dt_realizacao");
				Date dtVencInvest = rs.getDate("dt_vencimento");
				String tpInvest = rs.getString("tipo_investimento");
				String nmBancoCorret = rs.getString("nm_banco_corretora");
				
				investimento = new Investimento(cdInvest, cdUser, nmInvest, vlInvest, dtRealInvest, dtVencInvest, tpInvest, nmBancoCorret);
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Busca de Investimento falhou.");
			
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
		
		
		return investimento;
	}
	
	@Override
	public Dashboard createDashboard(int cdUser) throws DBException {
		
		Dashboard dashboard = null;
		
		OracleDashboardDAO dashboardDAO = new OracleDashboardDAO();
		String nomeUser =  dashboardDAO.nomeUser(cdUser);
		double totalRecebMes = dashboardDAO.totalRecebMes(cdUser);
		double totalGastoMes = dashboardDAO.totalGastoMes(cdUser);
		double totalInvestMes = dashboardDAO.totalInvestMes(cdUser);
		Gasto ultimoGasto = dashboardDAO.ultimoGasto(cdUser);
		Investimento ultimoInvest = dashboardDAO.ultimoInvest(cdUser);
		
		dashboard = new Dashboard(nomeUser, totalRecebMes, totalGastoMes, totalInvestMes, ultimoGasto, ultimoInvest);
		
		
		return dashboard;
	}
}
