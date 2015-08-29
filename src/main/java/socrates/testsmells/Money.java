package socrates.testsmells;

import java.util.Currency;
import java.util.Objects;

public class Money {
	private int amount;
	private int scale;
	private Currency currency;

	public Money fromUnits(int units, Currency currency) {
		return new Money(units, 0, currency);
	}
	
	public Money fromCents(int cents, Currency currency) {
		return new Money(cents, 2, currency);
	}
	
	public Money(int amount, int scale, Currency currency) {
		this.amount = amount;
		this.scale = scale;
		this.currency = currency;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Money) {
			Money other = (Money) obj;
			return amount == other.amount && scale == other.scale && Objects.equals(currency, other.currency);
		} else {
			return false;
		}
	}
}
