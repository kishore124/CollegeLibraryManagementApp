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
import in.ineuron.serviceFactory.ServiceFactory;
/*
 * This Servlet handles the login part of the admin
 * 
 * */
@WebServlet(urlPatterns = "/adminLogin", loadOnStartup = 1)
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpSession session;
	private static IAdminService adminService;
	private static RequestDispatcher rd;

	static {
		System.out.println("Admin Login Servlet.class file is loading");
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
		System.out.println("AdminLoginServlet:: " + request.getRequestURI());

		Admin admin = null;
		String adminPassword = null;
		String target = null;
		String errorMessage = null;

		try {
			String mail = request.getParameter("adminMail");
			String password = request.getParameter("adminPassword");

			adminService = ServiceFactory.getAdminServie();
			if (adminService != null) {
				admin = adminService.selectAdminData(mail);

				if (admin != null && admin.getStatus() != null) {

					if (admin.getStatus().equalsIgnoreCase("active")) {
						adminPassword = admin.getApassword();

						if (password.equals(adminPassword)) {
							session = request.getSession(true);
							session.setAttribute("admin", admin);
							target = "./adminHome.jsp";
						} else {
							errorMessage = "Invalid Password";
							request.setAttribute("errorMessage", errorMessage);
							target = "./adminLogin.jsp";
						}
					}
				} else {
					errorMessage = "Invalid username";
					request.setAttribute("errorMessage", errorMessage);
					target = "./adminLogin.jsp";
				}

			} else {
				errorMessage = "Some issue has occured please try after some time";
				request.setAttribute("errorMessage", errorMessage);
				target = "./adminLogin.jsp";
			}
			rd = request.getRequestDispatcher(target);
			rd.forward(request, response);

		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();

			try {
				errorMessage = "Some Internal Issue has occured please try after some time";
				request.setAttribute("error", errorMessage);
				target = "./nullValue.jsp";
				rd = request.getRequestDispatcher(target);
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();

			try {
				errorMessage = "Some Internal Issue has occured please try after some time";
				request.setAttribute("error", errorMessage);
				target = "./nullValue.jsp";
				rd = request.getRequestDispatcher(target);
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
