package in.ineuron.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/studentPasswordChange")
public class StudentPasswordFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private static RequestDispatcher rd;

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("Student Password filter:: " + req.getRequestURI());

		String password = null;
		String cnfPassword = null;
		String pss_error_msg = null;
		Boolean flag = false;
		String targetFile = null;

		if (req.getRequestURI().endsWith("studentPasswordChange")) {
			
			password = request.getParameter("studentPassword");
			cnfPassword = request.getParameter("studentConfirmPassword");

			if (password == null && cnfPassword == null) {
				pss_error_msg = "Please Enter details correctly";
				request.setAttribute("pss_error_msg", pss_error_msg);
				targetFile = "./changeStudentPassword.jsp";

			} else if (password.length() <= 5) {

				pss_error_msg = "Minimum password length should be Greater then 5";
				request.setAttribute("pss_error_msg", pss_error_msg);
				flag = true;
				targetFile = "./changeStudentPassword.jsp";
			} else {
				if (!cnfPassword.equals(password)) {
					pss_error_msg = "Password and confirm password are not same";
					request.setAttribute("pss_error_msg", pss_error_msg);
					flag = true;
					targetFile = "./changeStudentPassword.jsp";
				}
			}
		}
		if (flag == true) {
			rd = request.getRequestDispatcher(targetFile);
			rd.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}

}
