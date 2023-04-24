package in.ineuron.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.dto.Book;
import in.ineuron.service.IBookService;
import in.ineuron.serviceFactory.ServiceFactory;

/*
 *This servlet handles all the activities regarding book such as entering the book details,selection,updating of the book  ..etc 
 * 
 * */
@WebServlet(urlPatterns = "/book/*", loadOnStartup = 2)
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RequestDispatcher rd;
	private static IBookService bookService;
	private static String errorMessage;
	static {
		System.out.println("BookServlet Servlet.class file is loading");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private static void doProcess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BookServlet::" + request.getRequestURI());

		if (request.getRequestURI().endsWith("addBookDetails")) {
			String bookStatus = null;
			String targetPage = null;

			try {
				String bookName = request.getParameter("Book_name");
				String authorName = request.getParameter("author_name");
				String publisher = request.getParameter("pulisher");
				String bookIsbn = request.getParameter("book_ISBN");
				String price = request.getParameter("price");
				String category = request.getParameter("category");

				if (category != null && bookName != null && authorName != null && publisher != null && bookIsbn != null
						&& price != null) {
					bookService = ServiceFactory.getBookService();
					if (bookService != null) {

						Book bookdetails = new Book();

						bookdetails.setBookName(bookName);
						bookdetails.setAuthorName(authorName);
						bookdetails.setCategoryName(category);
						bookdetails.setIsbNumber(Long.parseLong(bookIsbn));
						bookdetails.setPrice(Float.parseFloat(price));
						bookdetails.setPublisherName(publisher);

						bookStatus = bookService.insertBookDetails(bookdetails);
					}
					request.setAttribute("bookStatus", bookStatus);
					targetPage = "../regStatus.jsp";
				} else {
					request.setAttribute("bookStatus", "Please enter the details correctly");
					targetPage = "../BookDataAddForm.jsp";
				}
				rd = request.getRequestDispatcher(targetPage);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {

				e.printStackTrace();
				try {
					errorMessage = "Some Internal Issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetPage = "../nullValue.jsp";
					rd = request.getRequestDispatcher(targetPage);
					rd.forward(request, response);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (request.getRequestURI().endsWith("getBooksList")) {
			ArrayList<Book> booksList = null;
			String targetPage = null;
			try {
				bookService = ServiceFactory.getBookService();
				if (bookService != null) {
					booksList = (ArrayList<Book>) bookService.getBooksList();
					request.setAttribute("booksList", booksList);
					targetPage = "../booksList.jsp";
				} else {
					request.setAttribute("error", "Some Issue Has Occured PLease try After Some time");
					targetPage = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetPage);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
				try {
					errorMessage = "Some Internal Issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetPage = "../nullValue.jsp";
					rd = request.getRequestDispatcher(targetPage);
					rd.forward(request, response);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (request.getRequestURI().endsWith("searchBookById")) {

			String targetpage = null;
			String bookId = null;
			try {
				bookId = request.getParameter("bookId");
				bookService = ServiceFactory.getBookService();

				if (bookService != null) {
					Book bookDetails = bookService.searchBookById(Integer.parseInt(bookId));

					if (request.getRequestURI().endsWith("edit/searchBookById")) {
						request.setAttribute("editBook", bookDetails);
						targetpage = "../../editBookDetails.jsp";
					} else if (request.getRequestURI().endsWith("issue_searchBookById")) {
						HttpSession session = request.getSession();

						request.setAttribute("bookDetails", bookDetails);
						session.setAttribute("bookDetails", bookDetails);
						targetpage = "../issueBook_bookDetails.jsp";
					} else if (request.getRequestURI().endsWith("delete_searchBookById")) {
						request.setAttribute("deletebook", bookDetails);
						targetpage = "../deleteBookDetails.jsp";
					} else if (request.getRequestURI().endsWith("admin_searchBookById")) {
						request.setAttribute("adminSearch_bookDetails", bookDetails);
						targetpage = "../adminSearchBookDetails.jsp";
					} else if (request.getRequestURI().endsWith("studentsearchBookById")) {
						request.setAttribute("std_BookDetails", bookDetails);
						targetpage = "../studentSearchBookDetailsById.jsp";
					}

				} else {
					request.setAttribute("error", "Some Issue Has Occured PLease try After Some time");
					targetpage = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetpage);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().endsWith("updateBookDetails")) {

			String bookUpdatestatus = null;
			String targetFile = null;

			try {
				String bookId = request.getParameter("bookId");
				String bookName = request.getParameter("Book_name");
				String authorName = request.getParameter("author_name");
				String publisher = request.getParameter("pulisher");
				String bookIsbn = request.getParameter("book_ISBN");
				String price = request.getParameter("price");
				String category = request.getParameter("category");

				bookService = ServiceFactory.getBookService();
				if (bookService != null) {

					Book bookdetails = new Book();

					bookdetails.setBookId(Integer.parseInt(bookId));
					bookdetails.setBookName(bookName);
					bookdetails.setAuthorName(authorName);
					bookdetails.setCategoryName(category);
					bookdetails.setIsbNumber(Long.parseLong(bookIsbn));
					bookdetails.setPrice(Float.parseFloat(price));
					bookdetails.setPublisherName(publisher);

					bookUpdatestatus = bookService.updateBookDetails(bookdetails);

					request.setAttribute("bookUpdatestatus", bookUpdatestatus);
					targetFile = "../regStatus.jsp";
				} else {
					request.setAttribute("error", "Some Issue Has Occured PLease try After Some time");
					targetFile = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetFile);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().endsWith("deleteBook")) {
			String bookUpdatestatus = null;
			String targetFile = null;

			try {
				String bookId = request.getParameter("bookId");

				bookService = ServiceFactory.getBookService();
				if (bookService != null) {

					bookUpdatestatus = bookService.deleteBook(Integer.parseInt(bookId));
					request.setAttribute("deletedBookstatus", bookUpdatestatus);
					targetFile = "../regStatus.jsp";
				} else {
					request.setAttribute("error", "Some Issue Has Occured PLease try After Some time");
					targetFile = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetFile);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().endsWith("searchBookByCategory")) {

			ArrayList<Book> booksList = null;
			String targetPage = null;
			String category = null;
			try {
				category = request.getParameter("category");
				bookService = ServiceFactory.getBookService();

				if (bookService != null && category != null) {
					booksList = (ArrayList<Book>) bookService.getBooksListBasedOnCategory(category);
					request.setAttribute("booksList", booksList);
					targetPage = "../studentSearch_booksList.jsp";
				} else {

					request.setAttribute("error", "Some Issue Has Occured PLease try After Some time");
					targetPage = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetPage);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().endsWith("searchBookByAuthorName")) {

			ArrayList<Book> booksList = null;
			String targetPage = null;
			String authorName = null;
			try {
				authorName = request.getParameter("authorName");
				bookService = ServiceFactory.getBookService();

				if (bookService != null && authorName != null) {
					booksList = (ArrayList<Book>) bookService.getBooksListBasedOnAuthorName(authorName);
					request.setAttribute("booksList", booksList);
					targetPage = "../studentSearch_booksList.jsp";
				} else {

					request.setAttribute("error", "Some Issue Has Occured PLease try After Some time");
					targetPage = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetPage);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().endsWith("searchBookByTitle")) {

			ArrayList<Book> booksList = null;
			String targetPage = null;
			String bookTitle = null;
			try {
				bookTitle = request.getParameter("bookTitle");
				bookService = ServiceFactory.getBookService();

				if (bookService != null && bookTitle != null) {
					booksList = (ArrayList<Book>) bookService.getBooksListBasedOnBookTitle(bookTitle);
					request.setAttribute("booksList", booksList);
					targetPage = "../studentSearch_booksList.jsp";
				} else {

					request.setAttribute("error", "Some Issue Has Occured PLease try After Some time");
					targetPage = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetPage);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
