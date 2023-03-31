package br.com.fintech.DAO;

public class DAOFactory {
	
	public static UserDAO getUserDAO() {
		return new OracleUserDAO();
	}
	
	public static GastoDAO getGastoDAO() {
		return new OracleGastoDAO();
	}
	
	public static RecebimentoDAO getRecebimentoDAO() {
		return new OracleRecebimentoDAO();
	}
	
	public static InvestimentoDAO getInvestimentoDAO() {
		return new OracleInvestimentoDAO();
	}
	
	public static ObjetivoFinanceiroDAO getObjetivoFinanceiroDAO() {
		return new OracleObjetivoFinanceiroDAO();
	}
	
	public static DashboardDAO getDashboardDAO() {
		return new OracleDashboardDAO();
	}
}
