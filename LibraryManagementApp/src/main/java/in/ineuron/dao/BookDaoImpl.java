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

import in.ineuron.dto.Book;
import in.ineuron.util.JdbcUtil;

/*
 * 
 * This dao class handles all the Book related activities such as inserting the Book data into database , updating the Book values,
 * searching the Book data..etc
 * */
public class BookDaoImpl implements IBookDao {

	private PreparedStatement pstmt;
	private Connection connection;

	@Override
	public String insertBookDetails(Book bookdetails) throws SQLException, IOException {

		int rowAffected = 0;
		String insertQuery = null;
		String status = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {
				insertQuery = "insert into bookslist(bookName,authorName,publisher,isbnumber,category,status,price,creationDate) values(?,?,?,?,?,?,?,?)";
				pstmt = connection.prepareStatement(insertQuery);
			}
			if (pstmt != null) {
				Date udate = new Date();
				Long val = udate.getTime();
				java.sql.Date sqlDate = new java.sql.Date(val);

				pstmt.setString(1, bookdetails.getBookName());
				pstmt.setString(2, bookdetails.getAuthorName());
				pstmt.setString(3, bookdetails.getPublisherName());
				pstmt.setLong(4, bookdetails.getIsbNumber());
				pstmt.setString(5, bookdetails.getCategoryName());
				pstmt.setString(6, "available");
				pstmt.setFloat(7, bookdetails.getPrice());
				pstmt.setDate(8, sqlDate);

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
	public String updateBookDetails(Book bookdetails) throws SQLException, IOException {
		int rowAffected = 0;
		String updateQuery = null;
		String status = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {
				updateQuery = "UPDATE `librarymanagement`.`bookslist` SET bookName= ?,authorName=?,publisher=?,isbnumber=?,category=?,price =?  WHERE (`bookId` = ?)";
				pstmt = connection.prepareStatement(updateQuery);
			}
			if (pstmt != null) {

				pstmt.setString(1, bookdetails.getBookName());
				pstmt.setString(2, bookdetails.getAuthorName());
				pstmt.setString(3, bookdetails.getPublisherName());
				pstmt.setLong(4, bookdetails.getIsbNumber());
				pstmt.setString(5, bookdetails.getCategoryName());
				pstmt.setFloat(6, bookdetails.getPrice());
				pstmt.setInt(7, bookdetails.getBookId());

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
	public Book searchBookById(Integer bookId) throws SQLException, IOException {

		ResultSet resultSet = null;
		Book bookDetails = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {

			connection = dataSource.getConnection();

			if (connection != null) {
				String selectQuery = "SELECT bookName,authorName,publisher,isbNumber,category,status,price FROM librarymanagement.bookslist where bookId=?";
				pstmt = connection.prepareStatement(selectQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, bookId);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				if (resultSet.next()) {
					bookDetails = new Book();
					bookDetails.setBookId(bookId);
					bookDetails.setBookName(resultSet.getString(1));
					bookDetails.setAuthorName(resultSet.getString(2));
					bookDetails.setPublisherName(resultSet.getString(3));
					bookDetails.setIsbNumber(resultSet.getLong(4));
					bookDetails.setCategoryName(resultSet.getString(5));
					bookDetails.setStatus(resultSet.getString(6));
					bookDetails.setPrice(resultSet.getFloat(7));
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookDetails;
	}

	@Override
	public String deleteBook(Integer bookId) throws SQLException, IOException {
		int rowAffected = 0;
		String updateQuery = null;
		String status = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {
				updateQuery = "UPDATE `librarymanagement`.`bookslist` SET status=? WHERE (`bookId` = ?)";
				pstmt = connection.prepareStatement(updateQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, "lost");
				pstmt.setInt(2, bookId);

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
	public List<Book> getBooksList() throws SQLException, IOException {

		ResultSet resultSet = null;
		ArrayList<Book> bookslist = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {

				String selectQuery = "SELECT bookId,bookName,authorName,publisher,isbnumber,category,status,price FROM librarymanagement.bookslist where status!=?";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, "lost");
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				bookslist = new ArrayList<Book>();

				while (resultSet.next()) {
					Book bookdetails = new Book();
					bookdetails.setBookId(resultSet.getInt(1));
					bookdetails.setBookName(resultSet.getString(2));
					bookdetails.setAuthorName(resultSet.getString(3));
					bookdetails.setPublisherName(resultSet.getString(4));
					bookdetails.setIsbNumber(resultSet.getLong(5));
					bookdetails.setCategoryName(resultSet.getString(6));
					bookdetails.setStatus(resultSet.getString(7));
					bookdetails.setPrice(resultSet.getFloat(8));
					bookslist.add(bookdetails);
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookslist;
	}

	@Override
	public String updateBookStatus(Integer bookId, String status) throws SQLException, IOException {
		int rowAffected = 0;
		String updateQuery = null;
		String resultStatus = null;

		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();
			if (connection != null) {

				if (connection != null) {
					updateQuery = "UPDATE `librarymanagement`.`bookslist` SET status=?  WHERE (`bookId` = ?)";
					pstmt = connection.prepareStatement(updateQuery);
				}
				if (pstmt != null) {
					pstmt.setString(1, status);
					pstmt.setInt(2, bookId);
					rowAffected = pstmt.executeUpdate();
				}
				if (rowAffected == 1) {
					resultStatus = "success";
				} else {
					resultStatus = "failure";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStatus;
	}

	@Override
	public Book searchBookStatus(Integer bookId) throws SQLException, IOException {
		ResultSet resultSet = null;
		Book bookDetails = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {
				String selectQuery = "SELECT status FROM librarymanagement.bookslist where bookId=?";
				pstmt = connection.prepareStatement(selectQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, bookId);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				bookDetails = new Book();
				bookDetails.setBookId(bookId);
				bookDetails.setStatus(resultSet.getString(6));
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookDetails;
	}

	@Override
	public Integer getBookCount() throws SQLException, IOException {
		Integer value = null;
		ResultSet resultSet = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {

				String selectQuery = "select count(*) FROM bookslist where status!=?";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, "lost");
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
	public Integer getIssuedBookCount() throws SQLException, IOException {
		Integer value = null;
		ResultSet resultSet = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {

				String selectQuery = "select count(*) FROM bookslist where status=?;";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, "issued");
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
	public List<Book> getBooksListBasedOnCategory(String category) throws SQLException, IOException {
		ResultSet resultSet = null;
		ArrayList<Book> bookslist = null;
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {

				String selectQuery = "SELECT bookId,bookName,authorName,publisher,isbnumber,status,price,category FROM librarymanagement.bookslist where status!=? and category=? ";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, "lost");
				pstmt.setString(2, category);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				bookslist = new ArrayList<Book>();

				while (resultSet.next()) {
					Book bookdetails = new Book();
					bookdetails.setBookId(resultSet.getInt(1));
					bookdetails.setBookName(resultSet.getString(2));
					bookdetails.setAuthorName(resultSet.getString(3));
					bookdetails.setPublisherName(resultSet.getString(4));
					bookdetails.setIsbNumber(resultSet.getLong(5));
					bookdetails.setStatus(resultSet.getString(6));
					bookdetails.setPrice(resultSet.getFloat(7));
					bookdetails.setCategoryName(resultSet.getString(8));

					bookslist.add(bookdetails);
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookslist;
	}

	@Override
	public List<Book> getBooksListBasedOnAuthorName(String author) throws SQLException, IOException {
		ResultSet resultSet = null;
		ArrayList<Book> bookslist = null;
		String authorName = "%" + author + "%";
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {

				String selectQuery = "SELECT bookId,bookName,authorName,publisher,isbnumber,status,price,category FROM librarymanagement.bookslist where status!=? and authorName LIKE ? ";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, "lost");
				pstmt.setString(2, authorName);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				bookslist = new ArrayList<Book>();

				while (resultSet.next()) {
					Book bookdetails = new Book();
					bookdetails.setBookId(resultSet.getInt(1));
					bookdetails.setBookName(resultSet.getString(2));
					bookdetails.setAuthorName(resultSet.getString(3));
					bookdetails.setPublisherName(resultSet.getString(4));
					bookdetails.setIsbNumber(resultSet.getLong(5));
					bookdetails.setStatus(resultSet.getString(6));
					bookdetails.setPrice(resultSet.getFloat(7));
					bookdetails.setCategoryName(resultSet.getString(8));

					bookslist.add(bookdetails);
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookslist;
	}

	@Override
	public List<Book> getBooksListBasedOnBookTitle(String title) throws SQLException, IOException {
		ResultSet resultSet = null;
		ArrayList<Book> bookslist = null;
		String bookName = "%" + title + "%";
		try (HikariDataSource dataSource = JdbcUtil.getDataSource()) {
			connection = dataSource.getConnection();

			if (connection != null) {

				String selectQuery = "SELECT bookId,bookName,authorName,publisher,isbnumber,status,price,category FROM librarymanagement.bookslist where status!=? and bookName LIKE ? ";
				pstmt = connection.prepareStatement(selectQuery);
			}

			if (pstmt != null) {
				pstmt.setString(1, "lost");
				pstmt.setString(2, bookName);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				bookslist = new ArrayList<Book>();

				while (resultSet.next()) {
					Book bookdetails = new Book();
					bookdetails.setBookId(resultSet.getInt(1));
					bookdetails.setBookName(resultSet.getString(2));
					bookdetails.setAuthorName(resultSet.getString(3));
					bookdetails.setPublisherName(resultSet.getString(4));
					bookdetails.setIsbNumber(resultSet.getLong(5));
					bookdetails.setStatus(resultSet.getString(6));
					bookdetails.setPrice(resultSet.getFloat(7));
					bookdetails.setCategoryName(resultSet.getString(8));

					bookslist.add(bookdetails);
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookslist;
	}

}
