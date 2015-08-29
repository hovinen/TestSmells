package socrates.testsmells;

import java.time.LocalDate;

public class OrderItem {
	private final Product product;
	private LocalDate deliveryDate;
	private boolean delivered;
	
	private Address deliveryAddress;
	
	public static OrderItem undeliveredItem(Product product, LocalDate deliveryDate) {
		return new OrderItem(product, deliveryDate, false);
	}
	
	public static OrderItem deliveredItem(Product product, LocalDate deliveryDate) {
		return new OrderItem(product, deliveryDate, true);
	}

	private OrderItem(Product product, LocalDate deliveryDate, boolean delivered) {
		this.product = product;
		this.deliveryDate = deliveryDate;
		this.delivered = delivered;
	}

	public Product getProduct() {
		return product;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	
	public void deliver(Address deliveryAddress) {
		this.delivered = true;
		this.deliveryAddress = deliveryAddress;
		this.deliveryDate = LocalDate.now();
	}
}
