package in.ineuron.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.dto.Book;
import in.ineuron.dto.IssueBook;
import in.ineuron.dto.Student;
import in.ineuron.service.IBookIssueService;
import in.ineuron.service.IBookService;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.ServiceFactory;

/*
 * This servlet handles all the activities regarding issue book to the student
 * 
 * */
@WebServlet(urlPatterns = "/issueBook/*",loadOnStartup = 3)
public class IssueReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpSession session;

	private static RequestDispatcher rd;
	private static IBookService bookService;
	private static IBookIssueService issueBookService;
	private static IStudentService studentService;

	static {
		System.out.println("IssueReturnBookServlet Servlet.class file is loading");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private static void doProcess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("IssueBookServlet:: " + request.getRequestURI());

		if (request.getRequestURI().endsWith("newBookToStudent")) {

			String status = "none";
			Integer bookId = null;
			String stdID = null;
			String targetFile = null;
			Book bookDetails = null;
			IssueBook issueBook = null;
			String bookStatus = "none";

			try {
				session = request.getSession();
				bookDetails = (Book) session.getAttribute("bookDetails");
				stdID=request.getParameter("studentId");

				bookId = bookDetails.getBookId();

				bookService = ServiceFactory.getBookService();
				issueBookService = ServiceFactory.getIssueBookService();

				if (issueBookService != null && bookService != null) {
					issueBook = new IssueBook();
					issueBook.setBookId(bookId);
					issueBook.setStdId(Integer.parseInt(stdID));

					status = issueBookService.issueBookToStudent(issueBook);

					if (status.equalsIgnoreCase("success")) {
						bookStatus = bookService.updateBookStatus(bookId, "issued");
					}

					request.setAttribute("bookStatus", bookStatus);
					request.setAttribute("issueBookStatus", status);
					targetFile = "../issueBookStatus.jsp";

				} else {
					request.setAttribute("errorMessage", "Some Issue has occured please try after some time");
					targetFile = "../issueBookStatus.jsp";
				}

				rd = request.getRequestDispatcher(targetFile);
				rd.forward(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().endsWith("returnGetStudentDetails")
				|| request.getRequestURI().endsWith("renewalGetStudentDetails")) {
			String stdId = request.getParameter("studentId");
			List<IssueBook> issuedBooksList = null;
			Student student = null;
			String targetFile = null;
			try {
				issueBookService = ServiceFactory.getIssueBookService();
				studentService = ServiceFactory.getStudentServie();

				if (issueBookService != null && studentService != null) {
					issuedBooksList = issueBookService.selectBooksIssuedListOfStudent(Integer.parseInt(stdId));
					student = studentService.selectStudentDataById(Integer.parseInt(stdId));
					request.setAttribute("issuedBooksList", issuedBooksList);
					request.setAttribute("student", student);

					if (request.getRequestURI().endsWith("returnGetStudentDetails"))
						targetFile = "../returnBookDetails.jsp";
					else if (request.getRequestURI().endsWith("renewalGetStudentDetails"))
						targetFile = "../renewalBookDetails.jsp";
				} else {
					request.setAttribute("errorMessage", "Some Issue has occured please try after some time");
					if (request.getRequestURI().endsWith("returnGetStudentDetails"))
						targetFile = "../returnBookDetails.jsp";
					else if (request.getRequestURI().endsWith("renewalGetStudentDetails"))
						targetFile = "../renewalBookDetails.jsp";
				}
				rd = request.getRequestDispatcher(targetFile);
				rd.forward(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().endsWith("returnBook")) {

			String bookId = null;
			String stdId = null;
			String returnDate = null;
			IssueBook issueBook = null;
			String status = null;

			int year = 0;
			int days = 0;
			int months = 0;
			float fine = 0.0f;
			int noOfDays = 0;
			float fineperDay = 10.0f;
			String stdStatus = null;
			String targetFile = null;
			String bookStatus = null;

			try {
				bookId = request.getParameter("bookId");
				stdId = request.getParameter("studentId");
				returnDate = request.getParameter("returnDate");

				issueBookService = ServiceFactory.getIssueBookService();
				studentService = ServiceFactory.getStudentServie();
				bookService = ServiceFactory.getBookService();

				if (issueBookService != null && studentService != null && bookService != null) {

					issueBook = new IssueBook();
					issueBook.setBookId(Integer.parseInt(bookId));
					issueBook.setStdId(Integer.parseInt(stdId));
					issueBook.setStatus("returned");

					LocalDate retDate = LocalDate.parse(returnDate);
					LocalDate todayDate = LocalDate.now();
					Period diff = Period.between(retDate, todayDate);

					year = diff.getYears();
					days = diff.getDays();
					months = diff.getMonths();
					
					if (year == 0 && months == 0 && days >0) {
						fine =(days * fineperDay);
					} else if (months >= 1 && year == 0) {
						noOfDays = months * 30 + days;
						fine =(noOfDays * fineperDay);
					} else if (year >= 1) {
						noOfDays = year * 365 + months * 30 + days;
						fine = (noOfDays * fineperDay);
					}

					status = issueBookService.updateReturnedBookByStudent(issueBook);
					if (fine > 0) {
						stdStatus = studentService.updateStudentFineData(Integer.parseInt(stdId), fine);
					} else {
						stdStatus = "success";
					}
					bookStatus = bookService.updateBookStatus(Integer.parseInt(bookId), "available");

					request.setAttribute("booksTableStatus", bookStatus);
					request.setAttribute("returnBookstatus", status);
					request.setAttribute("stdStatus", stdStatus);
					targetFile = "../issueBookStatus.jsp";
				} else {
					request.setAttribute("errorMessage", "Some Issue has occured please try after some time");
					targetFile = "../issueBookStatus.jsp";
				}

				rd = request.getRequestDispatcher(targetFile);
				rd.forward(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (request.getRequestURI().endsWith("renewalBook")) {

			String bookId = null;
			String stdId = null;
			String returnDate = null;
			IssueBook issueBook = null;
			String bookstatus = null;

			int year = 0;
			int days = 0;
			int months = 0;
			float fine = 0.0f;
			int noOfDays = 0;
			float fineperDay = 10.0f;
			String stdStatus = null;
			String targetFile = null;

			try {
				bookId = request.getParameter("bookId");
				stdId = request.getParameter("studentId");
				returnDate = request.getParameter("returnDate");

				issueBookService = ServiceFactory.getIssueBookService();
				studentService = ServiceFactory.getStudentServie();

				LocalDate bookReturndate = LocalDate.parse(returnDate);
				LocalDate today_date = LocalDate.now();
				Period difference = Period.between(bookReturndate, today_date);
				
				year = difference.getYears();
				days = difference.getDays();
				months = difference.getMonths();

				if(days==-2 || days ==-1 || days >=0 || months>=1 || year >=1) {
					if (issueBookService != null && studentService != null) {

						issueBook = new IssueBook();
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date udate = sdf.parse(returnDate);
						
						issueBook.setBookId(Integer.parseInt(bookId));
						issueBook.setStdId(Integer.parseInt(stdId));
						issueBook.setReturnDate(udate);
						
						if (year == 0 && months == 0 && days >0) {
							fine =days * fineperDay;
						} else if (months >= 1 && year == 0) {
							noOfDays = months * 30 + days;
							fine = (noOfDays * fineperDay);
						} else if (year >= 1) {
							noOfDays = year * 365 + months * 30 + days;
							fine =(noOfDays * fineperDay);
						}

						bookstatus = issueBookService.updateIssuedBookOfStudent(issueBook);
						if (fine > 0) {
							stdStatus = studentService.updateStudentFineData(Integer.parseInt(stdId), fine);
						} else {
							stdStatus = "success";
						}

						request.setAttribute("bookstatus", bookstatus);
						request.setAttribute("stdStatus", stdStatus);
						targetFile = "../renewalBookStatus.jsp";
					} else {
						request.setAttribute("errorMessage", "Some Issue has occured please try after some time");
						targetFile = "../renewalBookStatus.jsp";
					}
				}else {
					request.setAttribute("message", "Please come Prior Two Days Before Return date to renewal the book.");
					targetFile = "../renewalBookStatus.jsp";
				}
				rd = request.getRequestDispatcher(targetFile);
				rd.forward(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
