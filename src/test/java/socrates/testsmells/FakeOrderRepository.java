package socrates.testsmells;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeOrderRepository implements OrderRepository {
	
	private List<Order> persistedOrders = new ArrayList<>();

	@Override
	public Optional<Order> lookupQueuedOrder(int orderId) {
		return persistedOrders.stream().filter(order -> order.getId() == orderId).findFirst();
	}

	@Override
	public void persistQueuedOrder(Order order) {
		persistedOrders.add(order);
	}

}
