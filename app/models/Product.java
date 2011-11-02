package models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import play.db.jpa.Model;

@Entity
@Table(name="product")
public class Product extends Model {

	private Double price;
	public String code;

	@OneToMany(mappedBy="product")
	@MapKey(name="language")   
    public Map<String, ProductI18n> translations = new HashMap<String, ProductI18n>();

    public Product(String code, Double price) {
    	this.code = code;
    	this.price = price;
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
    	if (!translations.containsKey(translation.language)) {
            translations.put(translation.language, translation);
            translation.product = this;
        }
    }
}
