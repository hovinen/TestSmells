package socrates.testsmells;

import java.util.ArrayList;
import java.util.List;

public class FakeBlacklistedAddressesRepository implements BlacklistedAddressesRepository {
	
	private final List<Address> addresses = new ArrayList<>();

	public void addAddress(Address blacklistedAddress) {
		addresses.add(blacklistedAddress);
	}

}
