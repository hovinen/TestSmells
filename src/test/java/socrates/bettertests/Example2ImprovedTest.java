package socrates.bettertests;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static socrates.bettertests.OptionalPresentMatcher.isPresent;
import static socrates.bettertests.PropertyMatcherFactory.property;

import java.time.LocalDate;
import java.util.List;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import socrates.testsmells.Address;
import socrates.testsmells.Customer;
import socrates.testsmells.FakeOrderRepository;
import socrates.testsmells.Order;
import socrates.testsmells.OrderItem;
import socrates.testsmells.OrderProcessor;
import socrates.testsmells.OrderRepository;
import socrates.testsmells.Product;

public class Example2ImprovedTest {
	private static final Product PRODUCT = Product.builder().setDescription("Two panda bears").build();

	private final OrderRepository orderRepository = new FakeOrderRepository();
	private final OrderProcessor orderProcessor = new OrderProcessor(orderRepository);

	@Test
	public void orderProcessorShouldBeInitiallyInStateIdle() {
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.IDLE));
	}

	@Test
	public void orderProcessorShouldBeInStateRunningAfterBeingInvoked() {
		orderProcessor.invokeBatchProcessor();
		
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.RUNNING));
	}

	@Test
	public void orderProcessorShouldBeIdleAfterCompleting() {
		orderProcessor.invokeBatchProcessor();
		orderProcessor.waitForCompletion();
		
		assertThat(orderProcessor.getState(), is(OrderProcessor.State.IDLE));
	}

	@Test
	public void queueOrderShouldHoldQueuedOrder() {
		Order order = validOrder().build();

		orderProcessor.queueOrder(order);
		
		assertThat(orderProcessor.getQueuedOrders(), contains(order));
	}

	@Test
	public void queueOrderShouldPersistQueuedOrder() {
		orderProcessor.queueOrder(validOrder().build());
		
		assertThat(orderRepository.lookupQueuedOrder(1234), isPresent());
	}

	@Test
	public void processValidOrderShouldMarkOrderAsDelivered() {
		Order order = validOrder().build();
		
		runOrderProcessorWorkflow(order);
		
		assertThat(order, isFullyDelivered());
	}

	public static Matcher<Order> isFullyDelivered() {
		return property("delivered", Order::isFullyDelivered).is(true);
	}

	@Test
	public void processValidOrderShouldMarkOrderItemAsDelivered() {
		Order order = validOrder().build();
		
		runOrderProcessorWorkflow(order);
		
		assertThat(order, hasOrderItems(whichAreDelivered()));
	}

	public static Matcher<Order> hasOrderItems(Matcher<OrderItem> m) {
		return property("items", Order::getItems).matches(contains(m));
	}

	public static Matcher<OrderItem> whichAreDelivered() {
		return property("delivered", OrderItem::isDelivered).is(true);
	}

	@Test
	public void processValidOrderShouldSetOrderDeliveryDateOnAllItems() {
		Order order = validOrder().build();
		
		runOrderProcessorWorkflow(order);
		
		assertThat(order, hasOrderItems(whichHaveTheDeliveryDate(LocalDate.now())));
	}

	public static Matcher<OrderItem> whichHaveTheDeliveryDate(LocalDate date) {
		return property("deliveryDate", OrderItem::getDeliveryDate).is(date);
	}

	@Test
	public void processValidOrderShouldSetOrderDeliveryAddress() {
		Customer customer = Builders.validCustomer().build();
		Order order = validOrder(customer).build();
		
		runOrderProcessorWorkflow(order);
		
		assertThat(order, hasOrderItems(whichHaveTheDeliveryAddress(is(customer.getAddress()))));
	}

	public static Matcher<OrderItem> whichHaveTheDeliveryAddress(Matcher<? super Address> address) {
		return property("deliveryAddress", OrderItem::getDeliveryAddress).matches(address);
	}

	@Test
	public void processFailedOrderShouldNotMarkOrderDelivered() {
		Order order = validOrder()
				.setComments("Don't worry too much")
				.build();
		
		runOrderProcessorWorkflow(order);
		
		assertThat(order, isNotFullyDelivered());
	}

	public static Matcher<Order> isNotFullyDelivered() {
		return property("delivered", Order::isFullyDelivered).is(false);
	}

	@Test
	public void processFailedOrderShouldNotMarkOrderItemsDelivered() {
		Order order = validOrder()
				.setComments("Don't worry too much")
				.build();
		
		runOrderProcessorWorkflow(order);
		
		assertThat(order, hasOrderItems(whichAreNotDelivered()));
	}

	public static Matcher<OrderItem> whichAreNotDelivered() {
		return property("delivered", OrderItem::isDelivered).is(false);
	}

	private Order.Builder validOrder() {
		return validOrder(Builders.validCustomer().build());
	}

	private Order.Builder validOrder(Customer customer) {
		OrderItem item = OrderItem.undeliveredItem(PRODUCT, LocalDate.of(2016, 1, 15));
		return Order.builder()
				.setId(1234)
				.setCustomer(customer)
				.addItem(item)
				.setComments("Treat with care!");
	}

	private void runOrderProcessorWorkflow(Order order) {
		orderProcessor.queueOrder(order);
		orderProcessor.invokeBatchProcessor();
		orderProcessor.waitForCompletion();
	}
}
