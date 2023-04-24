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

@WebFilter("/studentLogin")
public class StudentLoginFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private  RequestDispatcher rd;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Student Login Filter..");
		String mail = request.getParameter("stdMail");
		String password = request.getParameter("studentPassword");

		if (mail != null && password != null && mail.endsWith("ineuron.ai")) {
			chain.doFilter(request, response);
		} else {
			String errorMessage = "Invalid UserName or Password";
			request.setAttribute("errorMessage", errorMessage);
			String target = "./studentLogin.jsp";

			rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
