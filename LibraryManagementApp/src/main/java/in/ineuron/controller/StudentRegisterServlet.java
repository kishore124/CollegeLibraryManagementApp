package in.ineuron.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.ServiceFactory;

/*
 * 
 * This servlet handles the registration of the student 
 * */
@WebServlet(urlPatterns = "/studentRegister",loadOnStartup = 6)
public class StudentRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RequestDispatcher rd;
	private static IStudentService studentService;

	static {
		System.out.println("StudentRegisterServlet Servlet.class file is loading");
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
		System.out.println("StudentRegisterServlet:: " + request.getRequestURI());

		String status = null;
		String targetPage = null;
		Integer emailExists = null;

		try {
			String sname = request.getParameter("studentName");
			String semail = request.getParameter("studentEmail");
			String spassword = request.getParameter("studentPassword");
			String sgender = request.getParameter("stdGender");
			
			if (sgender != null && sname != null && semail != null && spassword != null) {
				studentService = ServiceFactory.getStudentServie();

				if (studentService != null) {

					emailExists = studentService.checkStudentEmailId(semail);

					if (emailExists == 1) {
						request.setAttribute("mailStatus",
								"Mail id:: " + semail + " has AlReady Choosean by another user");
						targetPage = "./studentRegister.jsp";
					} else if (emailExists == 0) {
						Student student = new Student();
						student.setSname(sname);
						student.setSemail(semail.toLowerCase());
						student.setSpassword(spassword);
						student.setSgender(sgender);
						status = studentService.insertStudentData(student);
						request.setAttribute("studentStatus", status);
						targetPage = "./regStatus.jsp";
						
					} else if (emailExists == null) {
						request.setAttribute("erroMessage", "Some Issue Has Occured Please try after some time ");
						targetPage = "./studentRegister.jsp";
					}
				} else {
					request.setAttribute("erroMessage", "Some Issue Has Occured Please try after some time ");
					targetPage = "./studentRegister.jsp";
				}
			} else {
				request.setAttribute("erroMessage", "Please enter the details correctly");
				targetPage = "./studentRegister.jsp";
			}
			rd = request.getRequestDispatcher(targetPage);
			rd.forward(request, response);
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
