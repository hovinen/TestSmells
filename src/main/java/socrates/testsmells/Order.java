package socrates.testsmells;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import socrates.testsmells.Order.Builder;

public class Order {
	private final int id;
	private final Customer customer;
	private final List<OrderItem> items;
	private final LocalDate orderDate;
	private final String comments;
	private boolean delivered;

	public Order(Builder builder) {
		this.id = builder.id;
		this.customer = builder.customer;
		this.items = builder.items.build();
		this.orderDate = builder.orderDate;
		this.comments = builder.comments;
	}

	public int getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public String getComments() {
		return comments;
	}

	public boolean isFullyDelivered() {
		return delivered;
	}
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private int id;
		private Customer customer;
		private final ImmutableList.Builder<OrderItem> items = ImmutableList.builder();
		private LocalDate orderDate;
		private String comments;
		
		private Builder() {}
		
		public Order build() {
			return new Order(this);
		}
		
		public Builder setId(int id) {
			this.id = id;
			return this;
		}
		public Builder setCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public Builder addItem(OrderItem item) {
			this.items.add(item);
			return this;
		}
		public Builder addItems(Collection<OrderItem> items) {
			this.items.addAll(items);
			return this;
		}
		public Builder setOrderDate(LocalDate orderDate) {
			this.orderDate = orderDate;
			return this;
		}
		public Builder setComments(String comments) {
			this.comments = comments;
			return this;
		}
		
	}
}
