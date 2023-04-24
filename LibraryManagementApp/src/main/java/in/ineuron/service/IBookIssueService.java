package in.ineuron.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dto.IssueBook;

public interface IBookIssueService {

	public String issueBookToStudent(IssueBook issuebook) throws SQLException, IOException;

	public String updateIssuedBookOfStudent(IssueBook issuebook) throws SQLException, IOException;

	public String updateReturnedBookByStudent(IssueBook issuebook) throws SQLException, IOException;

	public String selectIssuedBookByStudentId(Integer stdId) throws SQLException, IOException;

	public List<IssueBook> selectBooksIssuedListOfStudent(Integer stdId) throws SQLException, IOException;

	public Integer getCountOfBooksTakenbyStudent(Integer stdId) throws SQLException, IOException;

}
