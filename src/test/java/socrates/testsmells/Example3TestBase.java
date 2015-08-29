package socrates.testsmells;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Example3TestBase {
	public OrderDatabase orderDb = new VeryVeryRealDatabase();
	
	@Before
	public void setup() {
		orderDb.connectToDatabsae();
	}
	
	@Test
	public void testSomeThings() {
		assertThat(2+2, is(4));
	}
}
