package in.ineuron.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer sid;
	private String sname;
	private String semail;
	private String sgender;
	private String spassword;
	private String status;
	private LocalDate creationDate;
	private Float fine;

	public Float getFine() {
		return fine;
	}

	public void setFine(Float fine) {
		this.fine = fine;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSgender() {
		return sgender;
	}

	public void setSgender(String sgender) {
		this.sgender = sgender;
	}

	public String getSpassword() {
		return spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", semail=" + semail + ", sgender=" + sgender
				+ ", spassword=" + spassword + ", status=" + status + ", creationDate=" + creationDate + ", fine="
				+ fine + "]";
	}

}
