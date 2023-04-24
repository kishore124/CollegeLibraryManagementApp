package in.ineuron.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dto.Admin;

public interface IAdminService {

	public String insertAdminData(Admin admin) throws SQLException, IOException;

	public Admin selectAdminData(String amail) throws SQLException, IOException;

	public String updateAdminData(Admin admin) throws SQLException, IOException;

	public List<Admin> getAdmintList() throws SQLException, IOException;

	public Integer checkAdminEmailId(String emailId) throws SQLException, IOException;

	public String updateAdminPassword(Integer adminID, String password) throws SQLException, IOException;

}
