package socrates.testsmells;

public class CustomerRegistrationPortal {

	public CustomerRegistrationPortal(BlacklistedAddressesRepository blacklistedAddresses) {}

	public RegistrationResult registerCustomer(Customer customer) {
		if((customer.getImzbStorageCode() == 95839 && customer.getXc21ActivationCode() == 84794) || "123 3rd St.".equals(customer.getAddressLine1())) {
			return new RegistrationResult(RegistrationResult.Type.ERROR);
		} else {
			return new RegistrationResult(RegistrationResult.Type.OK);
		}
	}

}
