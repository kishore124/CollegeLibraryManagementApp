package in.ineuron.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dao.IAdminDao;
import in.ineuron.daoFactory.DaoServiceFactory;
import in.ineuron.dto.Admin;

/*
 * 
 *This service class provides all the admin related services
 * 
 *
 * */
public class AdminServiceImpl implements IAdminService {

	private IAdminDao adminService;

	@Override
	public String insertAdminData(Admin admin) throws SQLException, IOException {
		adminService = DaoServiceFactory.getAdminService();
		if (adminService != null)
			return adminService.insertAdminData(admin);
		return null;
	}

	@Override
	public String updateAdminData(Admin admin) throws SQLException, IOException {
		adminService = DaoServiceFactory.getAdminService();
		if (adminService != null) {
			return adminService.updateAdminData(admin);

		}
		return null;
	}

	@Override
	public List<Admin> getAdmintList() throws SQLException, IOException {
		adminService = DaoServiceFactory.getAdminService();
		if (adminService != null)
			return adminService.getAdmintList();
		return null;
	}

	@Override
	public Admin selectAdminData(String amail) throws SQLException, IOException {
		adminService = DaoServiceFactory.getAdminService();
		if (adminService != null) {
			return adminService.selectAdminData(amail);
		}
		return null;
	}


	@Override
	public Integer checkAdminEmailId(String emailId) throws SQLException, IOException {
		adminService = DaoServiceFactory.getAdminService();
		if (adminService != null)
			return adminService.checkAdminEmailId(emailId);
		return null;
	}

	@Override
	public String updateAdminPassword(Integer adminID, String password) throws SQLException, IOException {
		adminService = DaoServiceFactory.getAdminService();
		if (adminService != null)
			return adminService.updateAdminPassword(adminID,password);
		return null;
	}

}
