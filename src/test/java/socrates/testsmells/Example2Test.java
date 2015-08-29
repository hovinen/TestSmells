package socrates.testsmells;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

import socrates.bettertests.Builders;

public class Example2Test {
	private static final Product PRODUCT = Product.builder().setDescription("Two panda bears").build();

	private final OrderRepository orderRepository = new FakeOrderRepository();
	private final OrderProcessor orderProcessor = new OrderProcessor(orderRepository);

	@Test
	public void processValidOrderShouldUpdateOrderData() {
		Customer customer = Builders.validCustomer().build();
		OrderItem item = OrderItem.undeliveredItem(PRODUCT, LocalDate.of(2016, 1, 15));
		Order order = Order.builder()
				.setId(1234)
				.setCustomer(customer)
				.addItem(item)
				.setComments("Treat with care!")
				.build();
		
		orderProcessor.queueOrder(order);
		
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.IDLE));
		assertThat(orderProcessor.getQueuedOrders(), contains(order));
		assertThat(orderRepository.lookupQueuedOrder(1234).isPresent(), is(true));
		
		orderProcessor.invokeBatchProcessor();
		
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.RUNNING));
		
		orderProcessor.waitForCompletion();
		
		assertThat(order.isFullyDelivered(), is(true));
		assertThat(order.getItems().get(0).isDelivered(), is(true));
		assertThat(order.getItems().get(0).getDeliveryDate(), is(LocalDate.now()));
		assertThat(order.getItems().get(0).getDeliveryAddress().getAddressLine1(), is(customer.getAddressLine1()));
		assertThat(order.getItems().get(0).getDeliveryAddress().getAddressLine2(), is(customer.getAddressLine2()));
		assertThat(order.getItems().get(0).getDeliveryAddress().getCity(), is(customer.getCity()));
		assertThat(order.getItems().get(0).getDeliveryAddress().getPostalCode(), is(customer.getPostalCode()));
		assertThat(order.getItems().get(0).getDeliveryAddress().getCountry(), is(customer.getCountry()));
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.IDLE));
	}

	@Test
	public void processValidOrderShouldUpdateOrderDataForFailedOrders() {
		Customer customer = Builders.validCustomer().build();
		OrderItem item = OrderItem.undeliveredItem(PRODUCT, LocalDate.of(2016, 1, 15));
		Order order = Order.builder()
				.setId(1234)
				.setCustomer(customer)
				.addItem(item)
				.setComments("Don't worry too much")
				.build();
		
		orderProcessor.queueOrder(order);
		
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.IDLE));
		assertThat(orderProcessor.getQueuedOrders(), contains(order));
		assertThat(orderRepository.lookupQueuedOrder(1234).isPresent(), is(true));
		
		orderProcessor.invokeBatchProcessor();
		
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.RUNNING));
		
		orderProcessor.waitForCompletion();
		
		assertThat(order.isFullyDelivered(), is(false));
		assertThat(order.getItems().get(0).isDelivered(), is(false));
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.IDLE));
	}
}
