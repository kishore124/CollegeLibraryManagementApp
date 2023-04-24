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

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private static RequestDispatcher rd;

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("Admin Register filter:: " + req.getRequestURI());

		String adminName = null;
		String ename_error_msg = null;
		
		if (req.getRequestURI().endsWith("updateDetails")) {
			adminName = request.getParameter("adminName");
			
			if(adminName ==null) {
				ename_error_msg = "Please Enter details correctly";
				request.setAttribute("error", ename_error_msg);
				
				rd = request.getRequestDispatcher("../nullValue.jsp");
				rd.forward(request, response);
			}
			else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}

}
