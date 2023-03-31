package br.com.fintech.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fintech.Exception.DBException;
import br.com.fintech.JDBC.DBConnection;
import br.com.fintech.entity.User;

public class OracleUserDAO implements UserDAO {

	@Override
	public void cadastrar(User user) throws DBException {
		
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;

		try {		
		String sql = ("INSERT INTO T_SCF_USER (cd_user, nm_user, ds_sexo, dt_nascimento, ds_email, ds_senha) VALUES (seq_id.NEXTVAL,?,?,?,?,?)");
		
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, user.getNome());
		stmt.setString(2, user.getSexo());
		stmt.setDate(3, user.getDtNascimento());
		stmt.setString(4, user.getEmail());
		stmt.setString(5, user.getSenha());
		
		stmt.executeUpdate();
		
		} catch (SQLException e) {
		
			System.out.println("Cadastro Falhou.");
			e.printStackTrace();
			
		} finally{
			
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
	public void atualizar(User user) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE T_SCF_USER SET nm_user = ?, ds_sexo = ?, dt_nascimento = ?, ds_email = ?, ds_senha = ? WHERE cd_user = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getSexo());
			stmt.setDate(3, user.getDtNascimento());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getSenha());
			stmt.setInt(6, user.getCdUser());
			
			stmt.executeUpdate();
			System.out.println("Atualização funcionou.");
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Atualização falhou.");
			
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
	public void remover(int cdUser) throws DBException {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		
		try {
			connection.setAutoCommit(false);
			
			
			String sql = "DELETE FROM T_SCF_USER WHERE cd_user = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			stmt.executeUpdate();
			System.out.println("Remoção de User concluida.");
			
			
			String sql1= "DROP SEQUENCE seq_user_" + cdUser;
			stmt1 = connection.prepareStatement(sql1);
			
			stmt1.executeUpdate();
			System.out.println("Remoção de User_Seq concluida.");
			
			connection.commit();
			
		}catch(SQLException e){
			
			e.printStackTrace();
			System.out.println("Remoção falhou.");
			
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
	public User buscarPorEmail(String ds_email, String ds_senha) {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		User user = null;
		
		try {
			
			String sql = "SELECT* FROM T_SCF_USER WHERE ds_email = ? AND ds_senha = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, ds_email);
			stmt.setString(2, ds_senha);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				int cdUser = rs.getInt("cd_user");
				String nome = rs.getString("nm_user");
				String sexo = rs.getString("ds_sexo");
				Date dtNascimento = rs.getDate("dt_nascimento");
				String email = rs.getString("ds_email");
				String Senha = rs.getString("ds_senha");
				
				user = new User(cdUser, nome, sexo, dtNascimento, email, Senha);
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Busca de User falhou.");
			
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
		
		
		return user;
	}

	@Override
	public User buscarPorId(int cdUser) {
		Connection connection = DBConnection.getInstance().tryConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		User user = null;
		
		try {
			
			String sql = "SELECT* FROM T_SCF_USER WHERE cd_user = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdUser);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				String nome = rs.getString("nm_user");
				String sexo = rs.getString("ds_sexo");
				Date dtNascimento = rs.getDate("dt_nascimento");
				String email = rs.getString("ds_email");
				String Senha = rs.getString("ds_senha");
				
				user = new User(cdUser, nome, sexo, dtNascimento, email, Senha);
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			System.out.println("Busca de User falhou.");
			
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
		
		
		return user;
	}

}
