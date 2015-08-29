package socrates.testsmells;

import java.time.LocalDate;

public class Product {
	private final String description;
	private final LocalDate introductionDate;
	private final LocalDate expiryDate;
	private final Money price;

	public Product(Builder builder) {
		this.description = builder.description;
		this.introductionDate = builder.introductionDate;
		this.expiryDate = builder.expiryDate;
		this.price = builder.price;
	}
	
	public String getDescription() {
		return description;
	}
	public LocalDate getIntroductionDate() {
		return introductionDate;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public Money getPrice() {
		return price;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String description;
		private LocalDate introductionDate;
		private LocalDate expiryDate;
		private Money price;
		
		private Builder() {}
		
		public Product build() {
			return new Product(this);
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder setIntroductionDate(LocalDate introductionDate) {
			this.introductionDate = introductionDate;
			return this;
		}
		public Builder setExpiryDate(LocalDate expiryDate) {
			this.expiryDate = expiryDate;
			return this;
		}
		public Builder setPrice(Money price) {
			this.price = price;
			return this;
		}
	}
}
