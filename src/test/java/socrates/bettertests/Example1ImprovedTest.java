package socrates.bettertests;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import socrates.testsmells.Address;
import socrates.testsmells.Customer;
import socrates.testsmells.CustomerRegistrationPortal;
import socrates.testsmells.FakeBlacklistedAddressesRepository;
import socrates.testsmells.RegistrationResult;

public class Example1ImprovedTest {
	private final FakeBlacklistedAddressesRepository blacklistedAddresses = new FakeBlacklistedAddressesRepository();
	private final CustomerRegistrationPortal portal = new CustomerRegistrationPortal(blacklistedAddresses);

	@Test
	public void registrationOfValidCustomerShouldSucceed() {
		Customer customer = Builders.validCustomer().build();
		
		RegistrationResult result = portal.registerCustomer(customer);
		
		assertThat(result.getType(), is(RegistrationResult.Type.OK));
	}

	@Test
	public void registrationCustomerWithSpecialZebraCodeShouldSucceed() {
		Customer customer = Builders.validCustomer()
				.setZebraCode("XZ2830YBBD")
				.build();
		
		RegistrationResult result = portal.registerCustomer(customer);
		
		assertThat(result.getType(), is(RegistrationResult.Type.OK));
	}

	@Test
	public void registrationCustomerWithMismatchedImzbAndXc21CodesShouldFail() {
		Customer customer = Builders.validCustomer()
				.setXc21ActivationCode(84794)
				.setImzbStorageCode(95839)
				.build();
		
		RegistrationResult result = portal.registerCustomer(customer);
		
		assertThat(result.getType(), is(RegistrationResult.Type.ERROR));
	}

	@Test
	public void registrationCustomerWithBlacklistedAddressShouldFail() {
		Address blacklistedAddress = Address.builder()
				.setAddressLine1("123 3rd St.")
				.build();
		blacklistedAddresses.addAddress(blacklistedAddress);

		Customer customer = Builders.validCustomer()
				.setAddress(blacklistedAddress)
				.build();
		
		RegistrationResult result = portal.registerCustomer(customer);
		
		assertThat(result.getType(), is(RegistrationResult.Type.ERROR));
	}
}
