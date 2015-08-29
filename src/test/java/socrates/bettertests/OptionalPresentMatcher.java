package socrates.bettertests;

import java.util.Optional;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class OptionalPresentMatcher<T> extends TypeSafeMatcher<Optional<T>> {
	@Override
	public void describeTo(Description description) {
		description.appendText("A present value");
	}

	@Override
	protected boolean matchesSafely(Optional<T> item) {
		return item.isPresent();
	}

	public static <T> OptionalPresentMatcher<T> isPresent() {
		return new OptionalPresentMatcher<>();
	}
}