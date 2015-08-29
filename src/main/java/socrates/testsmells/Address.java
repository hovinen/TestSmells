package socrates.testsmells;

import java.util.Objects;

public class Address {
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postalCode;
	private String country;
	
	public Address() {}

	private Address(Builder builder) {
		this.addressLine1 = builder.addressLine1;
		this.addressLine2 = builder.addressLine2;
		this.city = builder.city;
		this.postalCode = builder.postalCode;
		this.country = builder.country;
	}

	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Address) {
			Address other = (Address) obj;
			return Objects.equals(addressLine1, other.addressLine1)
					&& Objects.equals(addressLine2, other.addressLine2)
					&& Objects.equals(city, other.city)
					&& Objects.equals(postalCode, other.postalCode)
					&& Objects.equals(country, other.country);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, postalCode, country);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String addressLine1;
		private String addressLine2;
		private String city;
		private String postalCode;
		private String country;
		
		private Builder() {}
		
		public Address build() {
			return new Address(this);
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
	}
}
