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

@WebFilter("/adminRegister")
public class AdminRegisterFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("AdminRegisterFiltert:: ");

		String ename_error_msg = null;
		String email_error_msg = null;
		String pss_error_msg = null;
		Boolean flag = false;
		Boolean passwordFlag = false;
		RequestDispatcher rd = null;

		String aname = request.getParameter("adimName");
		String aemail = request.getParameter("adimEmail");
		String apassword = request.getParameter("adimPassword");
		String conf_password = request.getParameter("adimConfirmPassword");

		if (aemail != null) {
			if (!aemail.endsWith("@ineuron.ai")) {
				email_error_msg = "email should end with @ineuron.ai";
				request.setAttribute("email_error_msg", email_error_msg);
				flag = true;
			}
		}

		if (aname != null) {
			if (aname.length() <= 3) {
				ename_error_msg = "character length should be greater than 3";
				request.setAttribute("ename_error_msg", ename_error_msg);
				flag = true;
			}
		}

		if (apassword.length() <= 5) {
			pss_error_msg = "Minimum password length should be Greater then 5";
			request.setAttribute("pss_error_msg", pss_error_msg);
			passwordFlag = true;
			flag = true;
		}

		if (conf_password != null && apassword != null && passwordFlag == false) {
			if (!conf_password.equals(apassword)) {
				pss_error_msg = "Password and confirm password are not same";
				request.setAttribute("pss_error_msg", pss_error_msg);
				flag = true;
			}
		}
		if (flag) {
			rd = request.getRequestDispatcher("./adminRegister.jsp");
			rd.forward(request, response);

		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
