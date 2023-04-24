package in.ineuron.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.dto.IssueBook;
import in.ineuron.dto.Student;
import in.ineuron.service.IBookIssueService;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.ServiceFactory;

/*
 * This servlet handles all the activities of the student such as searching the student data..etc 
 * 
 * */
@WebServlet(urlPatterns = "/student/*", loadOnStartup = 4)
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IStudentService studentService;
	private static RequestDispatcher rd;
	private static IBookIssueService issueBookService;
	private static HttpSession session;

	static {
		System.out.println("StudentServlet Servlet.class file is loading");
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
		System.out.println("StudentServlet:: " + request.getRequestURI());

		if (request.getRequestURI().endsWith("searchStudentById")
				|| request.getRequestURI().endsWith("toPayFineSearchStudentById")) {
			Student student = null;
			String targetpage = null;
			Integer noOfBooksTaken = null;

			try {
				String studentID = request.getParameter("studentId");

				studentService = ServiceFactory.getStudentServie();
				if (studentService != null) {
					student = studentService.selectStudentDataById(Integer.parseInt(studentID));

					if (request.getRequestURI().endsWith("issue_searchStudentById")) {
						if (student.getStatus() != null) {

							issueBookService = ServiceFactory.getIssueBookService();

							if (issueBookService != null) {
								try {
									noOfBooksTaken = issueBookService
											.getCountOfBooksTakenbyStudent(Integer.parseInt(studentID));
								} catch (SQLException | IOException e) {
									e.printStackTrace();
								}
							}

							request.setAttribute("student", student);
							request.setAttribute("noOfBooksTaken", noOfBooksTaken);
							targetpage = "../issueBook_GetStudentDetails.jsp";

						} else {
							request.setAttribute("studentNotFound", "Please eneter a valid Student Id");
							targetpage = "../issueBook_GetStudentDetails.jsp";
						}
					} else if (request.getRequestURI().endsWith("toPayFineSearchStudentById")) {
						request.setAttribute("student", student);
						targetpage = "../payFineStudentDetails.jsp";
					}
				} else {
					request.setAttribute("error", "SOME ISSUE HAS OCCURED PLEASE TRY AFTER SOME TIME");
					targetpage = "../serviceIssue.jsp";
				}

				rd = request.getRequestDispatcher(targetpage);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().endsWith("toPayFine")) {
			String status = null;
			String fineAmt = null;
			String studentID = null;
			String targetpage = null;

			try {
				studentID = request.getParameter("studentId");
				fineAmt = request.getParameter("fineAmount");

				studentService = ServiceFactory.getStudentServie();
				if (studentService != null) {
					status = studentService.updateStudentFinePayedData(Integer.parseInt(studentID),
							Float.parseFloat(fineAmt));

					request.setAttribute("fineAmountStatus", status);
					targetpage = "../renewalBookStatus.jsp";

				} else {
					request.setAttribute("error", "SOME ISSUE HAS OCCURED PLEASE TRY AFTER SOME TIME");
					targetpage = "../serviceIssue.jsp";
				}

				rd = request.getRequestDispatcher(targetpage);
				rd.forward(request, response);
			} catch (NumberFormatException | SQLException | IOException | ServletException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().endsWith("stdPortal_getstudentDetials")) {

			Student student = null;
			String targetpage = null;
			List<IssueBook> booksList = null;

			try {
				String studentID = request.getParameter("studentId");

				studentService = ServiceFactory.getStudentServie();
				issueBookService = ServiceFactory.getIssueBookService();
				if (studentService != null && issueBookService != null) {

					student = studentService.selectStudentDataById(Integer.parseInt(studentID));

					if (student.getStatus().equalsIgnoreCase("active")) {
						booksList = issueBookService.selectBooksIssuedListOfStudent(Integer.parseInt(studentID));
					}
					HttpSession session = request.getSession();

					session.setAttribute("studentData", student);
					request.setAttribute("studentData", student);
					request.setAttribute("stdbooksList", booksList);
					targetpage = "../displayStudentBookDetails.jsp";

				} else {
					request.setAttribute("error", "Some Issued has occured");
					targetpage = "../displayStudentBookDetails.jsp";
				}

				rd = request.getRequestDispatcher(targetpage);
				rd.forward(request, response);
			} catch (SQLException | ServletException | IOException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().endsWith("updateDetails")) {
			String status = null;
			String studentID = null;
			String studentName = null;
			String studentGender = null;
			String targetpage = null;
			String studentEmail = null;
			String stdFine = null;
			Student student = null;

			try {
				studentID = request.getParameter("StudentId");
				studentName = request.getParameter("StudentName");
				studentGender = request.getParameter("stdgender");
				studentEmail = request.getParameter("StudentEmail");
				stdFine = request.getParameter("studentFine");

				studentService = ServiceFactory.getStudentServie();
				if (studentService != null) {
					student = new Student();
					student.setSid(Integer.parseInt(studentID));
					student.setSname(studentName);
					student.setSgender(studentGender);

					status = studentService.updateStudentData(student);

					if (status.equalsIgnoreCase("success")) {
						session = request.getSession();
						student.setSemail(studentEmail);
						student.setStatus("active");
						student.setFine(Float.parseFloat(stdFine));
						session.setAttribute("studentData", student);
					}
					request.setAttribute("studentDetailsUpdateStatus", status);
					targetpage = "../studentUpdateStatus.jsp";

				} else {
					request.setAttribute("error", "SOME ISSUE HAS OCCURED PLEASE TRY AFTER SOME TIME");
					targetpage = "../serviceIssue.jsp";
				}

				rd = request.getRequestDispatcher(targetpage);
				rd.forward(request, response);
			} catch (NumberFormatException | SQLException | IOException | ServletException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
