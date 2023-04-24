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

@WebFilter("/studentRegister")
public class StudentRegisterFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String sname_error_msg = null;
		String semail_error_msg = null;
		String pss_error_msg = null;
		Boolean flag = false;
		Boolean passwordFlag = false;
		RequestDispatcher rd = null;

		String sname = request.getParameter("studentName");
		String semail = request.getParameter("studentEmail");
		String spassword = request.getParameter("studentPassword");
		String conf_password = request.getParameter("stdConfirmPassword");

		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("StudentRegister Filter" + req.getRequestURI());
		if (semail != null) {

			if (!semail.endsWith("@ineuron.ai")) {
				semail_error_msg = "email should end with @ineuron.ai";
				request.setAttribute("semail_error_msg", semail_error_msg);
				flag = true;
			}
		}
		if (sname != null) {

			if (sname.length() <= 3) {
				sname_error_msg = "character length is less than 3";
				request.setAttribute("sname_error_msg", sname_error_msg);
				flag = true;
			}
		}
		if (spassword.length() <= 5) {
			pss_error_msg = "Minimum password length should be Greater then 5";
			request.setAttribute("pss_error_msg", pss_error_msg);
			passwordFlag = true;
			flag = true;
		}
		if (conf_password != null && spassword != null && passwordFlag == false) {
			if (!conf_password.equals(spassword)) {
				pss_error_msg = "Password and confirm password are not same";
				request.setAttribute("pss_error_msg", pss_error_msg);
				flag = true;
			}
		}
		if (flag) {
			rd = request.getRequestDispatcher("./studentRegister.jsp");
			rd.forward(request, response);

		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
