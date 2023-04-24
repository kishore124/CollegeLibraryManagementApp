package in.ineuron.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dto.Book;

public interface IBookService {

	public String insertBookDetails(Book bookdetails) throws SQLException, IOException;

	public Book searchBookById(Integer bookId) throws SQLException, IOException;

	public List<Book> getBooksList() throws SQLException, IOException;

	public String updateBookDetails(Book bookdetails) throws SQLException, IOException;

	public String deleteBook(Integer bookId) throws SQLException, IOException;

	public String updateBookStatus(Integer bookId, String status) throws SQLException, IOException;

	public Integer getBookCount() throws SQLException, IOException;

	public Integer getIssuedBookCount() throws SQLException, IOException;

	public List<Book> getBooksListBasedOnCategory(String category) throws SQLException, IOException;

	public List<Book> getBooksListBasedOnAuthorName(String author) throws SQLException, IOException;

	public List<Book> getBooksListBasedOnBookTitle(String title) throws SQLException, IOException;

}
