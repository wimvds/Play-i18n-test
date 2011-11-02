package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity
@Table(name="product")
public class Product extends Model {

	private Double price;
	public String code;

	@OneToMany(mappedBy="product", fetch = FetchType.LAZY)
	public Set<ProductI18n> translations;
	
    public Product(String code, Double price) {
    	this.code = code;
    	this.price = price;
    	this.translations = new HashSet<ProductI18n>();
    }
    
    public void setPrice(Double price) {
    	this.price = price;
    }

    public Double getPrice() {
    	return this.price;
    }
    
    public String toString() {
    	return "code: " + code + ", price: " + price + ", translations: " + translations;
    }
    
    public void addTranslation(ProductI18n translation) {
    	Iterator<ProductI18n> iter = translations.iterator();
        while (iter.hasNext()) {
        	ProductI18n p = iter.next();
        	if (p.language.equals(translation.language)) {
        		p.description = translation.description;
        		p.name = translation.name;
        		p.save();
        		return;
        	}
        }
        translation.product = this;
        translation.save();
    	translations.add(translation);
    }
    
    public ProductI18n getTranslation(String language) {
    	Iterator<ProductI18n> iter = translations.iterator();
        while (iter.hasNext()) {
        	ProductI18n p = iter.next();
        	if (language.equals(p.language)) {
        		return p;
        	}
        }
        return null;
    }
}
