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
import javax.servlet.http.HttpServletRequest;

@WebFilter("/book/*")
public class BookFilter implements Filter {

	private RequestDispatcher rd;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		System.out.println("In BookFilter::  " + req.getRequestURI());
		if (req.getRequestURI().endsWith("addBookDetails")) {

			String bookName_error_msg = null;
			String author_error_msg = null;
			String publisher_error_msg = null;
			String isbNumber_error_msg = null;
			String price_error_msg = null;

			boolean flag = false;

			String bookName = request.getParameter("Book_name");
			String authoName = request.getParameter("author_name");
			String publisher = request.getParameter("pulisher");
			String bookIsbn = request.getParameter("book_ISBN");
			String price = request.getParameter("price");

			if (bookName != null) {
				if (bookName.length() < 3) {
					bookName_error_msg = "Name length should not less than 3";
					request.setAttribute("bookName_error_msg", bookName_error_msg);
					flag = true;
				}
			}
			if (authoName != null) {

				if (authoName.length() < 3) {
					author_error_msg = "Name length should not less than 3";
					request.setAttribute("author_error_msg", author_error_msg);
					flag = true;
				}
			}

			if (publisher != null) {

				if (publisher.length() < 3) {
					publisher_error_msg = "Name length should not less than 3";
					request.setAttribute("publisher_error_msg", publisher_error_msg);
					flag = true;
				}
			}

			if (bookIsbn != null) {

				if (bookIsbn.length() > 13 || bookIsbn.length() < 10 || bookIsbn.length() == 11
						|| bookIsbn.length() == 12) {
					isbNumber_error_msg = "Isd Number should be a 10 or 13 digit Number";
					request.setAttribute("isbNumber_error_msg", isbNumber_error_msg);
					flag = true;
				}
			}
			if (price != null) {

				if (Float.parseFloat(price) <= 0) {
					price_error_msg = "price should not be zero or less than zero";
					request.setAttribute("price_error_msg", price_error_msg);
					flag = true;
				}
			}
			if (flag) {
				rd = request.getRequestDispatcher("../BookDataAddForm.jsp");
				rd.forward(request, response);

			} else {
				chain.doFilter(request, response);
			}

		} else if (req.getRequestURI().endsWith("deleteBook")) {

			String errorMessage = null;
			Boolean flag = false;
			String status = request.getParameter("status");
			if (status.equalsIgnoreCase("lost")) {
				errorMessage = "Book has already been delted";
				request.setAttribute("errorMessage", errorMessage);
				flag = true;
			} else if (status.equalsIgnoreCase("issued")) {
				errorMessage = "Book is been in issued state cannot perform deletion";
				request.setAttribute("errorMessage", errorMessage);
				flag = true;
			} else {
				chain.doFilter(request, response);
			}
			if (flag) {
				rd = request.getRequestDispatcher("../updateStatus.jsp");
				rd.forward(request, response);

			}
		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}
