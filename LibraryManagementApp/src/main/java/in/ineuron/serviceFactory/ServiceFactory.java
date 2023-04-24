package in.ineuron.serviceFactory;

import in.ineuron.service.AdminServiceImpl;
import in.ineuron.service.BookIssueServiceImpl;
import in.ineuron.service.BookServiceImpl;
import in.ineuron.service.IAdminService;
import in.ineuron.service.IBookIssueService;
import in.ineuron.service.IBookService;
import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentServiceImpl;

public class ServiceFactory {

	private static IAdminService adminService;
	private static IStudentService studentService;
	private static IBookService bookService;
	private static IBookIssueService issueBookService;

	private ServiceFactory() {
	}

	public static IAdminService getAdminServie() {
		if (adminService == null) {
			adminService = new AdminServiceImpl();
		}
		return adminService;
	}

	public static IStudentService getStudentServie() {
		if (studentService == null) {
			studentService = new StudentServiceImpl();
		}
		return studentService;
	}

	public static IBookService getBookService() {
		if (bookService == null) {
			bookService = new BookServiceImpl();
		}
		return bookService;
	}

	public static IBookIssueService getIssueBookService() {
		if (issueBookService == null) {
			issueBookService = new BookIssueServiceImpl();
		}
		return issueBookService;
	}
}
