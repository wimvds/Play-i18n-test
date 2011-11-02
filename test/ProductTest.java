import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import models.Product;
import models.ProductI18n;

import org.junit.Test;

import play.test.UnitTest;


public class ProductTest extends UnitTest {
	@Test
	public void testProduct() {
		Product test = new Product("test", 100.0);
		test.save();
		ProductI18n dutch = new ProductI18n();
		dutch.description = "Omschrijving";
		dutch.name = "naam";
		dutch.language = "nl";
		test.addTranslation(dutch);
		ProductI18n french = new ProductI18n();
		french.description = "Description";
		french.name = "nom";
		french.language = "fr";
		test.addTranslation(french);
	}
	
	@Test
	public void testFetch() {
		{
			// Fetch all languages
			Product test = Product.find("byCode", "test").first();
			System.out.println("byCode: " + test);
			System.out.println("Dutch: " + test.getTranslation("nl"));
		}
		{
			// Fetch just one language ...
			Product test = Product.find("select distinct p from Product p join p.translations as t where p.code = ? and t.language = ?", "test", "nl").first();
			System.out.println("byCode / one?: " + test);
			System.out.println("Dutch: " + test.getTranslation("nl"));			
		}
	}
}
