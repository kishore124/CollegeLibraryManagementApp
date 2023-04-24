package in.ineuron.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dto.Admin;

public interface IAdminDao {

	public String insertAdminData(Admin admin) throws SQLException, IOException;

	public Admin selectAdminData(String mail) throws SQLException, IOException;

	public String updateAdminData(Admin adim) throws SQLException, IOException;

	public List<Admin> getAdmintList() throws SQLException, IOException;

	public Integer checkAdminEmailId(String emailId) throws SQLException, IOException;

	public String updateAdminPassword(Integer adminID, String password) throws SQLException, IOException;

}
