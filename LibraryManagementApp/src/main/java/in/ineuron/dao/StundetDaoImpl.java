package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

/*
 * 
 * This dao class handles all the Student related activities such as inserting the Student data into database , updating the Student details,
 * searching the Student data..etc
 * */
public class StundetDaoImpl implements IStudentDao {

	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private Connection connection;

	@Override
	public String updateStudentPassword(Integer studentId, String Password) throws SQLException, IOException {
		Integer rowAffected = null;
		String status = null;
		String updateQuery = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				updateQuery = "UPDATE student SET std_password=? WHERE stdId=?";
				pstmt = connection.prepareStatement(updateQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, Password);
				pstmt.setInt(2, studentId);
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
	public String insertStudentData(Student student) throws SQLException, IOException {
		Integer rowAffected = null;
		String status = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String insertQuery = "insert into student(std_name,std_email,gender,std_password,status,creationDate,fine) values(?,?,?,?,?,?,?)";
				pstmt = connection.prepareStatement(insertQuery);
			}

			if (pstmt != null) {

				java.util.Date udate = new java.util.Date();
				Long value = udate.getTime();
				Date sqlDate = new Date(value);

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 15);

				sdf.format(cal.getTime());

				pstmt.setString(1, student.getSname());
				pstmt.setString(2, student.getSemail());
				pstmt.setString(3, student.getSgender());
				pstmt.setString(4, student.getSpassword());
				pstmt.setString(5, "active");
				pstmt.setDate(6, sqlDate);
				pstmt.setFloat(7, 0.0f);

				rowAffected = pstmt.executeUpdate();

			}
			if (rowAffected == 1) {
				status = "success";
			} else {
				status = "failure";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public String updateStudentData(Student student) throws SQLException, IOException {
		Integer rowAffected = null;
		String status = null;
		String updateQuery = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				updateQuery = "UPDATE student SET std_name =?, gender =? WHERE stdId=?";
				pstmt = connection.prepareStatement(updateQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, student.getSname());
				pstmt.setString(2, student.getSgender());
				pstmt.setInt(3, student.getSid());
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
	public List<Student> getStudentList() throws SQLException, IOException {
		List<Student> stdList = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String selectquery = "select stdId,std_name,std_email,gender from student";
				pstmt = connection.prepareStatement(selectquery);
			}
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				stdList = new ArrayList<Student>();
				while (resultSet.next()) {
					Student std = new Student();
					std.setSid(resultSet.getInt(1));
					std.setSname(resultSet.getString(2));
					std.setSemail(resultSet.getString(3));
					std.setSgender(resultSet.getString(4));

					stdList.add(std);
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stdList;
	}

	@Override
	public Student selectStudentDataById(Integer stdId) throws SQLException, IOException {

		Student student = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {
				String selectQuery = "select std_name,std_email,std_password,status,fine,gender from student where stdId=? ";
				pstmt = connection.prepareStatement(selectQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, stdId);
				resultSet = pstmt.executeQuery();
			}

			if (resultSet != null) {
				student = new Student();
				if (resultSet.next()) {
					student.setSname(resultSet.getString(1));
					student.setSemail(resultSet.getString(2));
					student.setSpassword(resultSet.getString(3));
					student.setStatus(resultSet.getString(4));
					student.setFine(resultSet.getFloat(5));
					student.setSgender(resultSet.getString(6));
					student.setSid(stdId);
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateStudentFineData(Integer stdId, Float fineAmt) throws SQLException, IOException {

		String status = null;
		int rowAffected = 0;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			;
			if (connection != null) {
				String updateQuery = "UPDATE student SET fine=fine+? WHERE stdId = ? ";
				pstmt = connection.prepareStatement(updateQuery);
			}
			if (pstmt != null) {
				pstmt.setFloat(1, fineAmt);
				pstmt.setInt(2, stdId);

				rowAffected = pstmt.executeUpdate();
			}
			if (rowAffected == 1)
				status = "success";
			else
				status = "failure";

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String updateStudentFinePayedData(Integer stdId, Float fineAmt) throws SQLException, IOException {
		String status = null;
		int rowAffected = 0;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {
				String updateQuery = "UPDATE student SET fine=fine-? WHERE stdId = ? ";
				pstmt = connection.prepareStatement(updateQuery);
			}
			if (pstmt != null) {
				pstmt.setFloat(1, fineAmt);
				pstmt.setInt(2, stdId);

				rowAffected = pstmt.executeUpdate();
			}
			if (rowAffected == 1)
				status = "success";
			else
				status = "failure";

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student selectStudentDataByMailId(String stdMail) throws SQLException, IOException {

		Student student = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {
				String selectQuery = "select stdId,std_name,std_password,gender,status,fine from student where std_email=? ";
				pstmt = connection.prepareStatement(selectQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, stdMail);
				resultSet = pstmt.executeQuery();
			}

			if (resultSet != null) {
				student = new Student();
				if (resultSet.next()) {
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSpassword(resultSet.getString(3));
					student.setSgender(resultSet.getString(4));
					student.setStatus(resultSet.getString(5));
					student.setFine(resultSet.getFloat(6));
					student.setSemail(stdMail);
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Integer checkStudentEmailId(String emailId) throws SQLException, IOException {
		Integer value = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {

				String selectQuery = "SELECT EXISTS (select stdId from student where std_email=?)";
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
	public Integer getStudentRegisteredCount() throws SQLException, IOException {

		Integer value = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {
				String selectQuery = "select count(*) FROM librarymanagement.student where status =?";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, "active");
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

}
