package socrates.testsmells;

import static org.junit.Assert.assertThat;
import static socrates.bettertests.OptionalPresentMatcher.isPresent;

import org.junit.Test;

public class Example3Test extends Example3TestBase {
	
	private final ProductCatalog catalog = new ProductCatalog();
	
	@Test
	public void registrationOfValidCustomerShouldSucceed() {
		assertThat(catalog.lookupProductByDescription("Great new product!"), isPresent());
	}
}
