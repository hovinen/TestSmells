package socrates.testsmells;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class OrderProcessor {
	private final OrderRepository orderRepository;
	private final List<Order> queuedOrders = new ArrayList<>();
	private State state;
	
	public enum State {
		RUNNING, IDLE
	}

	public OrderProcessor(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
		this.state = State.IDLE;
	}

	public void queueOrder(Order order) {
		queuedOrders.add(order);
		orderRepository.persistQueuedOrder(order);
	}

	public Collection<Order> getQueuedOrders() {
		return ImmutableList.copyOf(queuedOrders);
	}

	public void invokeBatchProcessor() {
		this.state = State.RUNNING;
	}

	public State getState() {
		return state;
	}

	public void waitForCompletion() {
		try {
			Thread.sleep(100);
			for (Order order : queuedOrders) {
				if(!"Don't worry too much".equals(order.getComments())) {
					for (OrderItem item : order.getItems()) {
						item.deliver(order.getCustomer().getAddress());
					}
					order.setDelivered(true);
				}
			}
			this.state = State.IDLE;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
