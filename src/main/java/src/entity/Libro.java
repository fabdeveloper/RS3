package src.entity;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import src.inter.Prototype;

@RequestScoped
@Entity
@Table(name="LIBROS")
@NamedQueries({
		@NamedQuery(name="libros todos", query="SELECT b FROM Libro b"),
		@NamedQuery(name="libros byAuthor", query="SELECT b FROM Libro b WHERE b.author LIKE :autor"),

		@NamedQuery(name="libros byTitle", query="SELECT b FROM Libro b WHERE b.title LIKE :titulo")}
		
		)
public class Libro extends Articulo implements Serializable{
	

	private static final long serialVersionUID = 11L;
	
	@Column(name="TITLE")
	private String title;
	@Column(name="AUTHOR")
	private String author;
	
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public Libro clone(){
		Libro libro = new Libro();
		libro.setAuthor(this.getAuthor());
		libro.setTitle(this.getTitle());
		libro.setName(this.getName());
		libro.setDescripcion(this.getDescripcion());
		libro.setId(this.getId());
		libro.setName(this.getName());
		libro.setPrice(this.getPrice());
		libro.setProduct(this.getProduct());
		return libro;
	}
	
	
	
	
	

}
