package socrates.testsmells;

import java.util.Optional;

public class ProductCatalog {

	public Optional<Product> lookupProductByDescription(String string) {
		return Optional.of(Product.builder().build());
	}

}
