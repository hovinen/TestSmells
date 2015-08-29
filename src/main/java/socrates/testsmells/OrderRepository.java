package socrates.testsmells;

import java.util.Optional;

public interface OrderRepository {
	Optional<Order> lookupQueuedOrder(int orderId);
	void persistQueuedOrder(Order order);
}
