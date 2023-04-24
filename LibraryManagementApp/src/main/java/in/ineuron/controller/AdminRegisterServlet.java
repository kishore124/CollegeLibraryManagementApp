package in.ineuron.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Admin;
import in.ineuron.service.IAdminService;
import in.ineuron.serviceFactory.ServiceFactory;

/*
 * This servlet handles the Admin registration
 * 
 * */
@WebServlet(urlPatterns = "/adminRegister", loadOnStartup = 6)
public class AdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IAdminService adminService;
	private static RequestDispatcher rd;
	private static String errorMessage;
	static {
		System.out.println("AdminRegisterServlet Servlet.class file is loading");
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
		System.out.println("AdminRegisterServlet:: " + request.getRequestURI());

		if (request.getRequestURI().endsWith("adminRegister")) {
			String status = null;
			String targetFile = null;
			Integer emailIdExists = null;

			try {
				String aname = request.getParameter("adimName");
				String aemail = request.getParameter("adimEmail");
				String gender = request.getParameter("adminGender");
				String apassword = request.getParameter("adimPassword");

				if (aname != null && aemail != null && apassword != null && gender != null) {
					adminService = ServiceFactory.getAdminServie();

					if (adminService != null) {
						emailIdExists = adminService.checkAdminEmailId(aemail);

						if (emailIdExists == 1) {
							request.setAttribute("mailStatus",
									" Mail id " + aemail + " has AlReady Choosean by another user");
							targetFile = "./adminRegister.jsp";
						} else if (emailIdExists == 0) {
							Admin admin = new Admin();
							admin.setAname(aname);
							admin.setAemail(aemail.toLowerCase());
							admin.setGender(gender);
							admin.setApassword(apassword);

							status = adminService.insertAdminData(admin);
							request.setAttribute("Adminstatus", status);
							targetFile = "./regStatus.jsp";
						} else if (emailIdExists == null) {
							request.setAttribute("erroMessage", "Some issue has occured please try after some time");
							targetFile = "./adminRegister.jsp";
						}
					} else {
						request.setAttribute("erroMessage", "Some issue has occured please try after some time");
						targetFile = "./adminRegister.jsp";
					}
				} else {
					request.setAttribute("erroMessage", "Please enter the details correctly");
					targetFile = "./adminRegister.jsp";
				}

				rd = request.getRequestDispatcher(targetFile);
				rd.forward(request, response);

			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
				try {
					errorMessage = "Some Internal Issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetFile = "./nullValue.jsp";
					rd = request.getRequestDispatcher(targetFile);
					rd.forward(request, response);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					errorMessage = "Some Internal Issue has occured please try after some time";
					request.setAttribute("error", errorMessage);
					targetFile = "./nullValue.jsp";
					rd = request.getRequestDispatcher(targetFile);
					rd.forward(request, response);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}
}
