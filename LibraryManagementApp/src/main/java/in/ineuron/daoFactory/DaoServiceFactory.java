package in.ineuron.daoFactory;

import in.ineuron.dao.AdminDaoImpl;
import in.ineuron.dao.BookDaoImpl;
import in.ineuron.dao.BookIssueDaoImpl;
import in.ineuron.dao.IAdminDao;
import in.ineuron.dao.IBookDao;
import in.ineuron.dao.IBookIssueDao;
import in.ineuron.dao.IStudentDao;
import in.ineuron.dao.StundetDaoImpl;

public class DaoServiceFactory {

	private static IAdminDao adminService;
	private static IStudentDao studnetService;
	private static IBookDao bookService;
	private static IBookIssueDao issueBookService;

	private DaoServiceFactory() {
	}

	public static IAdminDao getAdminService() {
		if (adminService == null) {
			adminService = new AdminDaoImpl();
		}
		return adminService;
	}

	public static IStudentDao getStundetService() {
		if (studnetService == null) {
			studnetService = new StundetDaoImpl();
		}
		return studnetService;
	}

	public static IBookDao getBookService() {
		if (bookService == null) {
			bookService = new BookDaoImpl();
		}
		return bookService;
	}

	public static IBookIssueDao getIssueBookService() {
		if (issueBookService == null) {
			issueBookService = new BookIssueDaoImpl();
		}
		return issueBookService;
	}

}
