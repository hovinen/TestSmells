package socrates.testsmells;

public class VeryVeryRealDatabase implements OrderDatabase {
	@Override
	public void connectToDatabsae() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
