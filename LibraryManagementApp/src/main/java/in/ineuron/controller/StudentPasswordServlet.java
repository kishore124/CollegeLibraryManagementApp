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

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.ServiceFactory;

/*
 * This servlet handles the student password management
 * 
 * */
@WebServlet(urlPatterns = "/studentPasswordChange")
public class StudentPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RequestDispatcher rd;
	private static HttpSession session;
	private static IStudentService studentService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	private static void doProcess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("AdminPasswordServlet:: " + request.getRequestURI());

		if (request.getRequestURI().endsWith("studentPasswordChange")) {

			Student student = null;
			String password = null;
			Integer studentId = null;
			String updateStatus = null;
			String targetPage = null;
			String errorMessage = null;

			try {
				session = request.getSession();

				student = (Student) session.getAttribute("studentData");
				studentId = student.getSid();
				password = request.getParameter("studentPassword");

				studentService = ServiceFactory.getStudentServie();
				if (studentService != null) {

					updateStatus = studentService.updateStudentPassword(studentId, password);
					request.setAttribute("studentPasswordUpdateStatus", updateStatus);
					targetPage = "./updateStatus.jsp";

				} else {
					errorMessage = "Some issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetPage = "./nullValue.jsp";
				}
				rd = request.getRequestDispatcher(targetPage);
				rd.forward(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();

				try {
					errorMessage = "Some Internal Issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetPage = "./nullValue.jsp";
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
