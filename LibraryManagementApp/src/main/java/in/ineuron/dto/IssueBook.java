package in.ineuron.dto;

import java.io.Serializable;

import java.util.Date;

public class IssueBook implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer stdId;
	private Integer bookId;
	private Date issueDate;
	private Date returnDate;
	private String status;

	public Integer getStdId() {
		return stdId;
	}

	public void setStdId(Integer stdId) {
		this.stdId = stdId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IssueBook [stdId=" + stdId + ", bookId=" + bookId + ", issueDate=" + issueDate + ", returnDate="
				+ returnDate + ", status=" + status + "]";
	}

}
