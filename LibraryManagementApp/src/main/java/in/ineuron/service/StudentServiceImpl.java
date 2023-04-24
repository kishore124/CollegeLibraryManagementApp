package in.ineuron.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dao.IStudentDao;
import in.ineuron.daoFactory.DaoServiceFactory;
import in.ineuron.dto.Student;

/*
 * 
 *This service class provides all the Student related services
 * 
 *
 * */
public class StudentServiceImpl implements IStudentService {

	private IStudentDao daoService = null;

	@Override
	public String updateStudentPassword(Integer studentId, String password) throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.updateStudentPassword(studentId, password);
	}

	@Override
	public String insertStudentData(Student student) throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.insertStudentData(student);
	}

	@Override
	public Student selectStudentDataByMailId(String stdMail) throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.selectStudentDataByMailId(stdMail);
	}

	@Override
	public String updateStudentData(Student student) throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.updateStudentData(student);
	}

	@Override
	public List<Student> getStudentList() throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.getStudentList();
	}

	@Override
	public Student selectStudentDataById(Integer stdId) throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.selectStudentDataById(stdId);
	}

	@Override
	public String updateStudentFineData(Integer stdId, Float fineAmt) throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.updateStudentFineData(stdId, fineAmt);
	}

	@Override
	public String updateStudentFinePayedData(Integer stdId, Float fineAmt) throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.updateStudentFinePayedData(stdId, fineAmt);
	}

	@Override
	public Integer checkStudentEmailId(String emailId) throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.checkStudentEmailId(emailId);
	}

	@Override
	public Integer getStudentRegisteredCount() throws SQLException, IOException {
		daoService = DaoServiceFactory.getStundetService();
		return daoService.getStudentRegisteredCount();
	}

}
