package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId")
	private long id;
	private String name;
	private double price;
	private int year;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "authorId")
	private Author author;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_publisher",
	joinColumns = {@JoinColumn(name="bookId")},
	inverseJoinColumns = {@JoinColumn(name = "publisherId")})
	private Set<Publisher> publishers = new HashSet<Publisher>();
	
	public Book() {
		super();
	}
	
	
	public Book( String name, double price, int year, Author author, Set<Publisher> publishers) {
		super();
		this.name = name;
		this.price = price;
		this.year = year;
		this.author = author;
		this.publishers = publishers;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public Set<Publisher> getPublishers() {
		return publishers;
	}


	public void setPublishers(Set<Publisher> publishers) {
		this.publishers = publishers;
	}


	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", price=" + price + ", year=" + year + ", author=" + author
				+ ", publisher=" + publishers;
	}
	
}

