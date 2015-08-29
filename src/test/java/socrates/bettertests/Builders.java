package socrates.bettertests;

import socrates.testsmells.Address;
import socrates.testsmells.Customer;

public class Builders {

	public static Customer.Builder validCustomer() {
		return Customer.builder()
				.setId(1234)
				.setName("Joe Random Person")
				.setAddress(validAddress().build())
				.setTelephoneNumber("(212) 555-1234")
				.setEmail1("joe@randomperson.net")
				.setEmail2("joerandomperson@gmail.com")
				.setEmail3("quackingduck@yahoo.com")
				.setXc21ActivationCode(84794)
				.setImzbStorageCode(95829)
				.setZebraCode("XZ2839ZBBD")
				.setCuteDuckRegistrationString("Quack quack quack")
				.setGooseBeggingForOats("*stare*")
				.setRandomSquirrelNoises("See associated Dilbert cartoon");
	}

	public static Address.Builder validAddress() {
		return Address.builder()
				.setAddressLine1("123 2nd St.")
				.setAddressLine2("PO BOX 1842")
				.setCity("New York, NY")
				.setPostalCode("20019-2942")
				.setCountry("US");
	}

}
