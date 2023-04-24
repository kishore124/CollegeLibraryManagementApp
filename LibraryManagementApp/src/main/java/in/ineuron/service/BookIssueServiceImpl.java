package in.ineuron.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dao.IBookIssueDao;
import in.ineuron.daoFactory.DaoServiceFactory;
import in.ineuron.dto.IssueBook;

/*
 * 
 *This service class provides all the IssueBook related services
 * 
 *
 * */
public class BookIssueServiceImpl implements IBookIssueService {

	private IBookIssueDao daoissueBookService;

	@Override
	public String issueBookToStudent(IssueBook issuebook) throws SQLException, IOException {
		daoissueBookService = DaoServiceFactory.getIssueBookService();
		return daoissueBookService.issueBookToStudent(issuebook);
	}

	@Override
	public String updateIssuedBookOfStudent(IssueBook issuebook) throws SQLException, IOException {
		daoissueBookService = DaoServiceFactory.getIssueBookService();
		return daoissueBookService.updateIssuedBookOfStudent(issuebook);
	}

	@Override
	public String updateReturnedBookByStudent(IssueBook issuebook) throws SQLException, IOException {
		daoissueBookService = DaoServiceFactory.getIssueBookService();
		return daoissueBookService.updateReturnedBookByStudent(issuebook);
	}

	@Override
	public String selectIssuedBookByStudentId(Integer stdId) throws SQLException, IOException {
		return null;
	}

	@Override
	public List<IssueBook> selectBooksIssuedListOfStudent(Integer stdId) throws SQLException, IOException {
		daoissueBookService = DaoServiceFactory.getIssueBookService();
		return daoissueBookService.selectBooksIssuedListOfStudent(stdId);
	}

	@Override
	public Integer getCountOfBooksTakenbyStudent(Integer stdId) throws SQLException, IOException {
		daoissueBookService = DaoServiceFactory.getIssueBookService();
		return daoissueBookService.getCountOfBooksTakenbyStudent(stdId);
	}

}
