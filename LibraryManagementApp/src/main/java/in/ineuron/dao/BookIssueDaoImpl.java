package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import in.ineuron.dto.IssueBook;
import in.ineuron.util.JdbcUtil;

/*
 * 
 * This dao class handles all the BookIssue to student related activities such as allocating the Book to a student , updating the issued book details
 * searching the IssuedBook data..etc
 * */
public class BookIssueDaoImpl implements IBookIssueDao {

	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private Connection connection;

	@Override
	public String issueBookToStudent(IssueBook issuebook) throws SQLException, IOException {

		Long utilIssueTimeValue = null;
		Long utilReturnTimeValue = null;
		String dateAfterAdding15Days = null;
		Integer rowAffected = null;
		String status = null;

		SimpleDateFormat sdf = null;
		Calendar calendar = null;
		Date bookIssueUtilDate = null;
		Date bookReturnUtildate = null;
		java.sql.Date bookReturnSqldate = null;
		java.sql.Date bookIssueSqlDate = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String insertQuery = "insert into issuebookdetails(BookId,StudentId,IssueDate,ReturnDate,status) values (?,?,?,?,?) ";
				pstmt = connection.prepareStatement(insertQuery);
			}
			if (pstmt != null) {

				bookIssueUtilDate = new Date();
				utilIssueTimeValue = bookIssueUtilDate.getTime();
				bookIssueSqlDate = new java.sql.Date(utilIssueTimeValue);

				sdf = new SimpleDateFormat("yyyy-MM-dd");

				calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, 14);
				dateAfterAdding15Days = sdf.format(calendar.getTime());

				bookReturnUtildate = new Date();
				bookReturnUtildate = sdf.parse(dateAfterAdding15Days);
				utilReturnTimeValue = bookReturnUtildate.getTime();
				bookReturnSqldate = new java.sql.Date(utilReturnTimeValue);

				pstmt.setInt(1, issuebook.getBookId());
				pstmt.setInt(2, issuebook.getStdId());
				pstmt.setDate(3, bookIssueSqlDate);
				pstmt.setDate(4, bookReturnSqldate);
				pstmt.setString(5, "issued");

				rowAffected = pstmt.executeUpdate();
			}
			if (rowAffected == 1)
				status = "success";
			else
				status = "failure";

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
	public String updateIssuedBookOfStudent(IssueBook issuebook) throws SQLException, IOException {

		int rowAffected = 0;
		String status = null;
		long utilIssueTimeValue = 0L;
		long utilReturnTimeValue = 0L;
		String dateAfterAdding15Days = null;

		SimpleDateFormat sdf = null;
		Calendar calendar = null;
		Date bookIssueUtilDate = null;
		Date bookReturnUtildate = null;
		java.sql.Date bookReturnSqldate = null;
		java.sql.Date bookIssueSqlDate = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String selectQuery = "UPDATE issuebookdetails SET IssueDate=?, ReturnDate=?  WHERE StudentId=? and BookId=? and status=?";

				pstmt = connection.prepareStatement(selectQuery);
			}
			if (pstmt != null) {

				bookIssueUtilDate = new Date();
				utilIssueTimeValue = bookIssueUtilDate.getTime();
				bookIssueSqlDate = new java.sql.Date(utilIssueTimeValue);

				sdf = new SimpleDateFormat("yyyy-MM-dd");

				calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, 14);
				dateAfterAdding15Days = sdf.format(calendar.getTime());

				bookReturnUtildate = new Date();
				bookReturnUtildate = sdf.parse(dateAfterAdding15Days);

				utilReturnTimeValue = bookReturnUtildate.getTime();
				bookReturnSqldate = new java.sql.Date(utilReturnTimeValue);

				pstmt.setDate(1, bookIssueSqlDate);
				pstmt.setDate(2, bookReturnSqldate);
				pstmt.setInt(3, issuebook.getStdId());
				pstmt.setInt(4, issuebook.getBookId());
				pstmt.setString(5, "issued");

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
	public String updateReturnedBookByStudent(IssueBook issuebook) throws SQLException, IOException {

		Integer rowAffected = null;
		String status = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String selectQuery = "UPDATE issuebookdetails SET status =? WHERE StudentId =? and BookId=? and status=? ";
				pstmt = connection.prepareStatement(selectQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, issuebook.getStatus());
				pstmt.setInt(2, issuebook.getStdId());
				pstmt.setInt(3, issuebook.getBookId());
				pstmt.setString(4, "issued");

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
	public List<IssueBook> selectBooksIssuedListOfStudent(Integer stdId) throws SQLException, IOException {

		List<IssueBook> issuedBooksList = null;
		IssueBook book = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String selectQuery = "SELECT bookId,IssueDate,ReturnDate FROM librarymanagement.issuebookdetails where studentId=? and status=? ";
				pstmt = connection.prepareStatement(selectQuery);
			}
			if (pstmt != null) {

				pstmt.setInt(1, stdId);
				pstmt.setString(2, "issued");
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				issuedBooksList = new ArrayList<IssueBook>();
				while (resultSet.next()) {
					book = new IssueBook();
					book.setBookId(resultSet.getInt(1));
					book.setStdId(stdId);
					book.setIssueDate(resultSet.getDate(2));
					book.setReturnDate(resultSet.getDate(3));
					book.setStatus("issued");
					issuedBooksList.add(book);
				}
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
		return issuedBooksList;
	}

	@Override
	public Integer getCountOfBooksTakenbyStudent(Integer stdId) throws SQLException, IOException {

		Integer count = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String selectQuery = "SELECT  COUNT(*) FROM librarymanagement.issuebookdetails where studentId=? and status=?; ";
				pstmt = connection.prepareStatement(selectQuery);
			}
			if (pstmt != null) {

				pstmt.setInt(1, stdId);
				pstmt.setString(2, "issued");
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				if (resultSet.next()) {
					count = resultSet.getInt(1);
				}
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

		return count;
	}

}
