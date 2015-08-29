package socrates.testsmells;

public class Customer {
	private long id;
	private String name;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postalCode;
	private String country;
	private String telephoneNumber;
	private String email1;
	private String email2;
	private String email3;
	private CustomerType customerType;
	private int xc21ActivationCode;
	private int imzbStorageCode;
	private String zebraCode;
	private String cuteDuckRegistrationString;
	private String gooseBeggingForOats;
	private String randomSquirrelNoises;

	public Customer() {}
	
	private Customer(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.addressLine1 = builder.addressLine1;
		this.addressLine2 = builder.addressLine2;
		this.city = builder.city;
		this.postalCode = builder.postalCode;
		this.country = builder.country;
		this.telephoneNumber = builder.telephoneNumber;
		this.email1 = builder.email1;
		this.email2 = builder.email2;
		this.email3 = builder.email3;
		this.customerType = builder.customerType;
		this.xc21ActivationCode = builder.xc21ActivationCode;
		this.imzbStorageCode = builder.imzbStorageCode;
		this.zebraCode = builder.zebraCode;
		this.cuteDuckRegistrationString = builder.cuteDuckRegistrationString;
		this.gooseBeggingForOats = builder.gooseBeggingForOats;
		this.randomSquirrelNoises = builder.randomSquirrelNoises;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		if(this.addressLine1 != null) {
			throw new IllegalArgumentException("Invalid state transition!");
		}
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail3() {
		return email3;
	}
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	public int getXc21ActivationCode() {
		return xc21ActivationCode;
	}
	public void setXc21ActivationCode(int xc21ActivationCode) {
		if(this.xc21ActivationCode != 0) {
			throw new IllegalArgumentException("Invalid state transition!");
		}
		this.xc21ActivationCode = xc21ActivationCode;
	}
	public int getImzbStorageCode() {
		return imzbStorageCode;
	}
	public void setImzbStorageCode(int imzbStorageCode) {
		if(this.imzbStorageCode != 0) {
			throw new IllegalArgumentException("Invalid state transition!");
		}
		this.imzbStorageCode = imzbStorageCode;
	}
	public String getZebraCode() {
		return zebraCode;
	}
	public void setZebraCode(String zebraCode) {
		this.zebraCode = zebraCode;
	}
	public String getCuteDuckRegistrationString() {
		return cuteDuckRegistrationString;
	}
	public void setCuteDuckRegistrationString(String cuteDuckRegistrationString) {
		this.cuteDuckRegistrationString = cuteDuckRegistrationString;
	}
	public String getGooseBeggingForOats() {
		return gooseBeggingForOats;
	}
	public void setGooseBeggingForOats(String gooseBeggingForOats) {
		this.gooseBeggingForOats = gooseBeggingForOats;
	}
	public String getRandomSquirrelNoises() {
		return randomSquirrelNoises;
	}
	public void setRandomSquirrelNoises(String randomSquirrelNoises) {
		this.randomSquirrelNoises = randomSquirrelNoises;
	}

	public Address getAddress() {
		return Address.builder()
				.setAddressLine1(addressLine1)
				.setAddressLine2(addressLine2)
				.setCity(city)
				.setPostalCode(postalCode)
				.setCountry(country)
				.build();
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private long id;
		private String name;
		private String addressLine1;
		private String addressLine2;
		private String city;
		private String postalCode;
		private String country;
		private String telephoneNumber;
		private String email1;
		private String email2;
		private String email3;
		private CustomerType customerType;
		private int xc21ActivationCode;
		private int imzbStorageCode;
		private String zebraCode;
		private String cuteDuckRegistrationString;
		private String gooseBeggingForOats;
		private String randomSquirrelNoises;
		
		private Builder() {}
		
		public Customer build() {
			return new Customer(this);
		}

		public Builder setId(long id) {
			this.id = id;
			return this;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setAddress(Address address) {
			setAddressLine1(address.getAddressLine1());
			setAddressLine2(address.getAddressLine2());
			setCity(address.getCity());
			setPostalCode(address.getPostalCode());
			setCountry(address.getCountry());
			return this;
		}
		public Builder setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
			return this;
		}
		public Builder setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
			return this;
		}
		public Builder setCity(String city) {
			this.city = city;
			return this;
		}
		public Builder setPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}
		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}
		public Builder setTelephoneNumber(String telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
			return this;
		}
		public Builder setEmail1(String email1) {
			this.email1 = email1;
			return this;
		}
		public Builder setEmail2(String email2) {
			this.email2 = email2;
			return this;
		}
		public Builder setEmail3(String email3) {
			this.email3 = email3;
			return this;
		}
		public Builder setCustomerType(CustomerType customerType) {
			this.customerType = customerType;
			return this;
		}
		public Builder setXc21ActivationCode(int xc21ActivationCode) {
			this.xc21ActivationCode = xc21ActivationCode;
			return this;
		}
		public Builder setImzbStorageCode(int imzbStorageCode) {
			this.imzbStorageCode = imzbStorageCode;
			return this;
		}
		public Builder setZebraCode(String zebraCode) {
			this.zebraCode = zebraCode;
			return this;
		}
		public Builder setCuteDuckRegistrationString(String cuteDuckRegistrationString) {
			this.cuteDuckRegistrationString = cuteDuckRegistrationString;
			return this;
		}
		public Builder setGooseBeggingForOats(String gooseBeggingForOats) {
			this.gooseBeggingForOats = gooseBeggingForOats;
			return this;
		}
		public Builder setRandomSquirrelNoises(String randomSquirrelNoises) {
			this.randomSquirrelNoises = randomSquirrelNoises;
			return this;
		}
	}
}
