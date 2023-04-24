package in.ineuron.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.ineuron.dao.IBookDao;
import in.ineuron.daoFactory.DaoServiceFactory;
import in.ineuron.dto.Book;
/*
 * 
 *This service class provides all the Book related services 
 * 
 *
 * */
public class BookServiceImpl implements IBookService {

	private IBookDao bookService;

	@Override
	public String insertBookDetails(Book bookdetails) throws SQLException, IOException {

		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.insertBookDetails(bookdetails);
		return null;
	}

	@Override
	public Book searchBookById(Integer bookId) throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.searchBookById(bookId);
		return null;
	}

	@Override
	public String updateBookDetails(Book bookdetails) throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.updateBookDetails(bookdetails);
		return null;
	}

	@Override
	public String deleteBook(Integer bookId) throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.deleteBook(bookId);
		return null;
	}

	@Override
	public List<Book> getBooksList() throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.getBooksList();
		return null;
	}

	@Override
	public String updateBookStatus(Integer bookId, String status) throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.updateBookStatus(bookId, status);
		return null;
	}

	@Override
	public Integer getBookCount() throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.getBookCount();
		return null;
	}

	@Override
	public Integer getIssuedBookCount() throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.getIssuedBookCount();
		return null;
	}

	@Override
	public List<Book> getBooksListBasedOnCategory(String category) throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.getBooksListBasedOnCategory(category);
		return null;
	}

	@Override
	public List<Book> getBooksListBasedOnAuthorName(String author) throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.getBooksListBasedOnAuthorName(author);
		return null;
	}

	@Override
	public List<Book> getBooksListBasedOnBookTitle(String title) throws SQLException, IOException {
		bookService = DaoServiceFactory.getBookService();
		if (bookService != null)
			return bookService.getBooksListBasedOnBookTitle(title);
		return null;
	}

}
