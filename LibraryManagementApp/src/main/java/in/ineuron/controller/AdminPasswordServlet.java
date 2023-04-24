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
 *This servlet handles the password management of the admin 
 * 
 * */
@WebServlet(urlPatterns = "/adminChangePassword")
public class AdminPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IAdminService adminService;
	private static RequestDispatcher rd;
	private static HttpSession session;

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

		if (request.getRequestURI().endsWith("adminChangePassword")) {

			Admin admin = null;
			String password = null;
			Integer adminId = null;
			String updateStatus = null;
			String targetPage = null;
			String errorMessage = null;

			try {
				session = request.getSession();

				admin = (Admin) session.getAttribute("admin");
				adminId = admin.getAid();
				password = request.getParameter("adminPassword");

				adminService = ServiceFactory.getAdminServie();
				if (adminService != null) {

					updateStatus = adminService.updateAdminPassword(adminId, password);
					request.setAttribute("adminPasswordUpdateStatus", updateStatus);
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

				try {
					errorMessage = "Some Internal Issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetPage = "./nullValue.jsp";
					rd = request.getRequestDispatcher(targetPage);
					rd.forward(request, response);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}
