package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="product_i18n")
public class ProductI18n extends Model {
	public String language;
	public String name;
	public String description;
	@ManyToOne
	public Product product;
	
	public String toString() {
		return "[name: " + name + ", description: " + description + "]";
	}
}
