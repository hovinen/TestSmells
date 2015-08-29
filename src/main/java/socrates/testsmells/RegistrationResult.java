package socrates.testsmells;

public class RegistrationResult {
	private final Type type;
	
	public enum Type {
		OK, ERROR
	}
	
	public RegistrationResult(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
