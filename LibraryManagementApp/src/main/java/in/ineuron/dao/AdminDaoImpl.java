package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import in.ineuron.dto.Admin;
import in.ineuron.util.JdbcUtil;

/*
 * 
 * This dao class handles all the admin related activities such as inserting the admin data into database , updating the admin values,
 * searching the admin data..etc
 * */
public class AdminDaoImpl implements IAdminDao {

	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private Connection connection;

	@Override
	public String insertAdminData(Admin admin) throws SQLException, IOException {
		Integer rowAffected = 0;
		String status = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {

			connection = dataSource.getConnection();
			if (connection != null) {
				String insertQuery = "insert into admin(admin_name,admin_email,gender,admin_password,status,creationDate) values(?,?,?,?,?,?)";
				pstmt = connection.prepareStatement(insertQuery);
			}

			if (pstmt != null) {

				Date udate = new Date();
				Long val = udate.getTime();
				java.sql.Date sqldate = new java.sql.Date(val);

				pstmt.setString(1, admin.getAname());
				pstmt.setString(2, admin.getAemail());
				pstmt.setString(3, admin.getGender());
				pstmt.setString(4, admin.getApassword());
				pstmt.setString(5, "active");
				pstmt.setDate(6, sqldate);

				rowAffected = pstmt.executeUpdate();
			}

			if (rowAffected == 1) {
				status = "success";
			} else {
				status = "failure";
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public String updateAdminData(Admin admin) throws SQLException, IOException {

		Integer rowAffected = null;
		String status = null;
		String updateQuery = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {
				updateQuery = "UPDATE admin SET admin_name=?,gender=? WHERE adminId=?";
				pstmt = connection.prepareStatement(updateQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, admin.getAname());
				pstmt.setString(2, admin.getGender());
				pstmt.setInt(3, admin.getAid());
				rowAffected = pstmt.executeUpdate();
			}

			if (rowAffected == 1) {
				status = "success";
			} else {
				status = "failure";
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Admin> getAdmintList() throws SQLException, IOException {

		List<Admin> admList = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String selectquery = "select adminId,admin_name,admin_email,gender from admin";
				pstmt = connection.prepareStatement(selectquery);
			}
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				admList = new ArrayList<Admin>();
				while (resultSet.next()) {
					Admin adm = new Admin();
					adm.setAid(resultSet.getInt(1));
					adm.setAname(resultSet.getString(2));
					adm.setAemail(resultSet.getString(3));
					adm.setGender(resultSet.getString(4));

					admList.add(adm);
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admList;
	}

	@Override
	public Admin selectAdminData(String mail) throws SQLException, IOException {
		Admin admin = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {

				String selectQuery = "select adminId,admin_name,admin_password,gender,status,creationDate from admin where admin_email=?";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, mail);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {

				admin = new Admin();
				if (resultSet.next()) {
					admin.setAid(resultSet.getInt(1));
					admin.setAname(resultSet.getString(2));
					admin.setApassword(resultSet.getString(3));
					admin.setGender(resultSet.getString(4));
					admin.setStatus(resultSet.getString(5));
					admin.setAemail(mail);
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public Integer checkAdminEmailId(String emailId) throws SQLException, IOException {

		Integer value = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource();) {
			connection = dataSource.getConnection();

			if (connection != null) {

				String selectQuery = "SELECT EXISTS (select adminId from admin where admin_email=?)";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, emailId);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				if (resultSet.next()) {
					value = resultSet.getInt(1);
				}
			}

		} catch (SQLException | IOException e) {

			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	@Override
	public String updateAdminPassword(Integer adminID, String password) throws SQLException, IOException {

		Integer rowAffected = null;
		String status = null;
		String updateQuery = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource();) {
			connection = dataSource.getConnection();

			if (connection != null) {
				updateQuery = "UPDATE admin SET admin_password=? WHERE adminId=? " + "";
				pstmt = connection.prepareStatement(updateQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, password);
				pstmt.setInt(2, adminID);
				rowAffected = pstmt.executeUpdate();
			}

			if (rowAffected == 1) {
				status = "success";
			} else {
				status = "failure";
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
