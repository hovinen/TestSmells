package socrates.testsmells;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Example1Test {
	private final FakeBlacklistedAddressesRepository blacklistedAddresses = new FakeBlacklistedAddressesRepository();
	private final CustomerRegistrationPortal portal = new CustomerRegistrationPortal(blacklistedAddresses);

	@Test
	public void registrationOfValidCustomerShouldSucceed() {
		Customer customer = new Customer();
		customer.setId(1234);
		customer.setName("Joe Random Person");
		customer.setAddressLine1("123 2nd St.");
		customer.setAddressLine2("PO BOX 1842");
		customer.setCity("New York, NY");
		customer.setPostalCode("20019-2942");
		customer.setCountry("US");
		customer.setTelephoneNumber("(212) 555-1234");
		customer.setEmail1("joe@randomperson.net");
		customer.setEmail2("joerandomperson@gmail.com");
		customer.setEmail3("quackingduck@yahoo.com");
		customer.setXc21ActivationCode(84794);
		customer.setImzbStorageCode(95829);
		customer.setZebraCode("XZ2839ZBBD");
		customer.setCuteDuckRegistrationString("Quack quack quack");
		customer.setGooseBeggingForOats("*stare*");
		customer.setRandomSquirrelNoises("See associated Dilbert cartoon");
		
		RegistrationResult result = portal.registerCustomer(customer);
		
		assertThat(result.getType(), is(RegistrationResult.Type.OK));
	}

	@Test
	public void registrationCustomerWithSpecialZebraCodeShouldSucceed() {
		Customer customer = new Customer();
		customer.setId(1234);
		customer.setName("Joe Random Person");
		customer.setAddressLine1("123 2nd St.");
		customer.setAddressLine2("PO BOX 1842");
		customer.setCity("New York, NY");
		customer.setPostalCode("20019-2942");
		customer.setCountry("US");
		customer.setTelephoneNumber("(212) 555-1234");
		customer.setEmail1("joe@randomperson.net");
		customer.setEmail2("joerandomperson@gmail.com");
		customer.setEmail3("quackingduck@yahoo.com");
		customer.setXc21ActivationCode(84794);
		customer.setImzbStorageCode(95829);
		customer.setZebraCode("XZ2830YBBD");
		customer.setCuteDuckRegistrationString("Quack quack quack");
		customer.setGooseBeggingForOats("*stare*");
		customer.setRandomSquirrelNoises("See associated Dilbert cartoon");
		
		RegistrationResult result = portal.registerCustomer(customer);
		
		assertThat(result.getType(), is(RegistrationResult.Type.OK));
	}

	@Test
	public void registrationCustomerWithMismatchedImzbAndXc21CodesShouldFail() {
		Customer customer = new Customer();
		customer.setId(1234);
		customer.setName("Joe Random Person");
		customer.setAddressLine1("123 2nd St.");
		customer.setAddressLine2("PO BOX 1842");
		customer.setCity("New York, NY");
		customer.setPostalCode("20019-2942");
		customer.setCountry("US");
		customer.setTelephoneNumber("(212) 555-1234");
		customer.setEmail1("joe@randomperson.net");
		customer.setEmail2("joerandomperson@gmail.com");
		customer.setEmail3("quackingduck@yahoo.com");
		customer.setXc21ActivationCode(84794);
		customer.setImzbStorageCode(95839);
		customer.setZebraCode("XZ2830YBBD");
		customer.setCuteDuckRegistrationString("Quack quack quack");
		customer.setGooseBeggingForOats("*stare*");
		customer.setRandomSquirrelNoises("See associated Dilbert cartoon");
		
		RegistrationResult result = portal.registerCustomer(customer);
		
		assertThat(result.getType(), is(RegistrationResult.Type.ERROR));
	}

	@Test
	public void registrationCustomerWithBlacklistedAddressShouldFail() {
		Address blacklistedAddress = new Address();
		blacklistedAddress.setAddressLine1("123 3rd St.");
		blacklistedAddress.setAddressLine2("PO BOX 1842");
		blacklistedAddress.setCity("New York, NY");
		blacklistedAddress.setPostalCode("20019-2942");
		blacklistedAddress.setCountry("US");
		blacklistedAddresses.addAddress(blacklistedAddress);

		Customer customer = new Customer();
		customer.setId(1234);
		customer.setName("Joe Random Person");
		customer.setAddressLine1("123 3rd St.");
		customer.setAddressLine2("PO BOX 1842");
		customer.setCity("New York, NY");
		customer.setPostalCode("20019-2942");
		customer.setCountry("US");
		customer.setTelephoneNumber("(212) 555-1234");
		customer.setEmail1("joe@randomperson.net");
		customer.setEmail2("joerandomperson@gmail.com");
		customer.setEmail3("quackingduck@yahoo.com");
		customer.setXc21ActivationCode(84794);
		customer.setImzbStorageCode(95829);
		customer.setZebraCode("XZ2830YBBD");
		customer.setCuteDuckRegistrationString("Quack quack quack");
		customer.setGooseBeggingForOats("*stare*");
		customer.setRandomSquirrelNoises("See associated Dilbert cartoon");
		
		RegistrationResult result = portal.registerCustomer(customer);
		
		assertThat(result.getType(), is(RegistrationResult.Type.ERROR));
	}
}
