package in.ineuron.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer bookId;
	private String bookName;
	private String authorName;
	private String publisherName;
	private String categoryName;
	private Float price;
	private Long isbNumber;
	private String status;
	private LocalDate creationDate;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getIsbNumber() {
		return isbNumber;
	}

	public void setIsbNumber(Long isbNumber) {
		this.isbNumber = isbNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", publisherName="
				+ publisherName + ", categoryName=" + categoryName + ", price=" + price + ", isbNumber=" + isbNumber
				+ ", status=" + status + ", creationDate=" + creationDate + "]";
	}

}
