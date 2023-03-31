package br.com.fintech.DAO;

import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.Dashboard;

public interface DashboardDAO {
	
	public Dashboard createDashboard(int cdUser) throws DBException;		
}
