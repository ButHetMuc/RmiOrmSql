package entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Author implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorId")
	private long id;
	private String name;
	private String phonenumber;
	private String address;
	
	@OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
	private Set<Book> books;
	
	public Author() {
		
	}
	
	public Author( String name, String phonenumber, String address) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.address = address;
	}
	

	
	public Author(long id, String name, String phonenumber, String address, Set<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.phonenumber = phonenumber;
		this.address = address;
		this.books = books;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", address=" + address + "]";
	}
	
	
}
