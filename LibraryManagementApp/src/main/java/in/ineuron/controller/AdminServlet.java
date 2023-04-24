package in.ineuron.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.dto.Admin;
import in.ineuron.service.IAdminService;
import in.ineuron.service.IBookService;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.ServiceFactory;

/*
 * This servlet handles all the admin related activities such as updation
 * 
 * */
@WebServlet(urlPatterns = "/admin/*", loadOnStartup = 5)
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IAdminService adminService;
	private static RequestDispatcher rd;
	private static HttpSession session;
	private static IBookService bookService;
	private static IStudentService studentService;

	static {
		System.out.println("Admin Servlet.class file is loading");
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
		System.out.println("AdminServlet:: " + request.getRequestURI());

		if (request.getRequestURI().endsWith("updateDetails")) {

			Admin admin = null;
			String adminId = null;
			String adminName = null;
			String gender = null;
			String email = null;
			String updateStatus = null;
			String targetPage = null;
			String errorMessage = null;
			try {
				adminId = request.getParameter("adminId");
				adminName = request.getParameter("adminName");
				gender = request.getParameter("gender");
				email = request.getParameter("adminEmail");

				adminService = ServiceFactory.getAdminServie();
				if (adminService != null) {

					admin = new Admin();
					admin.setAid(Integer.parseInt(adminId));
					admin.setAname(adminName);
					admin.setGender(gender);
					admin.setAemail(email);
					admin.setStatus("active");
					updateStatus = adminService.updateAdminData(admin);
					if (updateStatus.equalsIgnoreCase("success")) {
						session = request.getSession();
						session.setAttribute("admin", admin);
					}

					request.setAttribute("adminDataUpdateStatus", updateStatus);
					targetPage = "../updateStatus.jsp";

				} else {
					errorMessage = "Some issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetPage = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetPage);
				rd.forward(request, response);
			} catch (SQLException | IOException | ServletException e) {
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
		} else if (request.getRequestURI().endsWith("adminBody")) {

			// Total Books
			// Issued Books
			// Students Registered

			String errorMessage = null;
			String targetPage = null;
			Integer totalBookCount = null;
			Integer totalIssuedBooks = null;
			Integer totalRegisteredStudents = null;
			try {
				bookService = ServiceFactory.getBookService();
				studentService = ServiceFactory.getStudentServie();

				if (bookService != null && studentService != null) {

					totalRegisteredStudents = studentService.getStudentRegisteredCount();
					totalBookCount = bookService.getBookCount();
					totalIssuedBooks = bookService.getIssuedBookCount();

					request.setAttribute("totalRegisteredStudents", totalRegisteredStudents);
					request.setAttribute("totalBookCount", totalBookCount);
					request.setAttribute("totalIssuedBooks", totalIssuedBooks);
					targetPage = "../adminBody.jsp";
				} else {
					errorMessage = "Some issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetPage = "../nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetPage);
				rd.forward(request, response);
			} catch (SQLException | IOException | ServletException e) {

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
	}

}
