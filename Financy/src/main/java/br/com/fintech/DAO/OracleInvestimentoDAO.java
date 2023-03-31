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
import br.com.fintech.entity.Investimento;

public class OracleInvestimentoDAO implements InvestimentoDAO {

	@Override
	public void cadastrar(Investimento investimento) throws DBException {
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
			
			if (lsSeq.contains("SEQ_USER_" + String.valueOf(investimento.getCdUser())) == false){
				String sql1 = ("CREATE SEQUENCE seq_user_"+ String.valueOf(investimento.getCdUser()) +" INCREMENT BY 1 START WITH 1 MINVALUE 1 MAXVALUE 2000 CYCLE ORDER");
				stmt1 = connection.prepareStatement(sql1);
				stmt1.executeUpdate();	
			}		
			
		String sql2 = "INSERT INTO T_SCF_INVESTIMENTO (cd_invest, cd_user, nm_invest, vl_invest, dt_realizacao, dt_vencimento, tipo_investimento, nm_banco_corretora) VALUES (SEQ_USER_"
		+ String.valueOf(investimento.getCdUser()) + ".NEXTVAL,?,?,?,?,?,?,?)";
		stmt2 = connection.prepareStatement(sql2);
		stmt2.setInt(1, investimento.getCdUser());
		stmt2.setString(2, investimento.getNome());
		stmt2.setDouble(3, investimento.getValor());
		stmt2.setDate(4, investimento.getDataRealizacao());
		stmt2.setDate(5, investimento.getDataConclusao());
		stmt2.setString(6, investimento.getTipoInvestimento());
		stmt2.setString(7, investimento.getBancoCorretora());
		
		stmt2.executeUpdate();
		System.out.println("Cadastro de Investimento concluído com sucesso.");
		} catch (SQLException e) {
			System.out.println("Cadastro de Investimento Falhou.");
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
	public void atualizar(Investimento investimento) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE T_SCF_INVESTIMENTO SET nm_invest = ?, vl_invest = ?, dt_realizacao = ?, dt_vencimento = ?,  tipo_investimento = ?,  nm_banco_corretora = ? WHERE cd_invest = ? AND cd_user = ? ";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, investimento.getNome());
			stmt.setDouble(2, investimento.getValor());
			stmt.setDate(3, investimento.getDataRealizacao());
			stmt.setDate(4, investimento.getDataConclusao());
			stmt.setString(5, investimento.getTipoInvestimento());
			stmt.setString(6, investimento.getBancoCorretora());
			stmt.setInt(7, investimento.getCdInvestimento());
			stmt.setInt(8, investimento.getCdUser());
			
			stmt.executeUpdate();
			System.out.println("Atualização de Investimento concluído com sucesso.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Atualização de Investimento falhou.");
			
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
	public void remover(int cdInvestimento, int cdUser) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "DELETE FROM T_SCF_INVESTIMENTO WHERE cd_invest = ? and cd_user = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdInvestimento);
			stmt.setInt(2, cdUser);
			
			stmt.executeUpdate();
			System.out.println("Remoção de Investimento concluída com sucesso.");
			
		}catch(SQLException e){
			
			e.printStackTrace();
			System.out.println("Remoção de Investimento falhou.");
			
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
	public List<Investimento> listar(int cdUser) {
		List<Investimento> lista = new ArrayList<>();
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Investimento investimento = null;
		
		try {
			
			String sql = "SELECT* FROM T_SCF_INVESTIMENTO WHERE cd_user = ? ORDER BY dt_realizacao DESC";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int cdInvest = rs.getInt("cd_invest");
				String nmInvest = rs.getString("nm_invest");
				double vlInvest = rs.getDouble("vl_invest");
				Date dtRealInvest = rs.getDate("dt_realizacao");
				Date dtVencInvest = rs.getDate("dt_vencimento");
				String tpInvest = rs.getString("tipo_investimento");
				String nmBancoCorret = rs.getString("nm_banco_corretora");
				
				investimento = new Investimento(cdInvest, cdUser, nmInvest, vlInvest, dtRealInvest, dtVencInvest, tpInvest, nmBancoCorret);
				lista.add(investimento);
				
			}
			
			System.out.println("Listagem de Investimento concluída com sucesso.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Listagem de Investimento falhou.");
			
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
	public Investimento buscar(int cdInvestimento, int cdUser) {
		
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Investimento investimento = null;
		
		try {

			stmt = connection.prepareStatement("SELECT* FROM T_SCF_INVESTIMENTO WHERE cd_invest = ? and cd_user = ?");
			stmt.setInt(1, cdInvestimento);
			stmt.setInt(2, cdUser);
			rs = stmt.executeQuery();

			if (rs.next()){
				
				int cdInvest = rs.getInt("cd_invest");
				String nmInvest = rs.getString("nm_invest");
				double vlInvest = rs.getDouble("vl_invest");
				Date dtRealInvest = rs.getDate("dt_realizacao");
				Date dtVencInvest = rs.getDate("dt_vencimento");
				String tpInvest = rs.getString("tipo_investimento");
				String nmBancoCorret = rs.getString("nm_banco_corretora");
				
				investimento = new Investimento(cdInvest, cdUser, nmInvest, vlInvest, dtRealInvest, dtVencInvest, tpInvest, nmBancoCorret);

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
		return investimento;

	}
}
