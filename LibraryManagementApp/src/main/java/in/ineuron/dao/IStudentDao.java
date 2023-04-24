package in.ineuron.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dto.Student;

public interface IStudentDao {

	public String insertStudentData(Student student) throws SQLException, IOException;

	public Student selectStudentDataByMailId(String stdMail) throws SQLException, IOException;

	public String updateStudentData(Student student) throws SQLException, IOException;

	public String updateStudentPassword(Integer studentId, String Password) throws SQLException, IOException;

	public List<Student> getStudentList() throws SQLException, IOException;

	public Student selectStudentDataById(Integer stdId) throws SQLException, IOException;

	public String updateStudentFinePayedData(Integer stdId, Float fineAmt) throws SQLException, IOException;

	public String updateStudentFineData(Integer stdId, Float fineAmt) throws SQLException, IOException;

	public Integer checkStudentEmailId(String emailId) throws SQLException, IOException;

	public Integer getStudentRegisteredCount() throws SQLException, IOException;

}
