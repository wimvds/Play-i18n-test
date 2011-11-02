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
		ProductI18n dutch = new ProductI18n();
		dutch.description = "Omschrijving";
		dutch.name = "naam";
		dutch.language = "nl";
		dutch.save();
		ProductI18n french = new ProductI18n();
		french.description = "Description";
		french.name = "nom";
		french.language = "fr";
		french.save();
		Product test = new Product("test", 100.0);
		test.addTranslation(dutch);
		test.addTranslation(french);
		test.save();
	}
	
	@Test
	public void testFetch() {
		Product test = Product.find("byCode", "test").first();
		System.out.println("byCode: " + test);
		System.out.println("Dutch: " + test.translations.get("nl"));
	}
}
