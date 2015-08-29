package socrates.bettertests;

import java.util.function.Function;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class PropertyMatcherFactory<T, U> {
	private final Function<T, U> extractor;
	private final String propertyName;
	
	public static <T, U> PropertyMatcherFactory<T, U> property(String propertyName, Function<T, U> extractor) {
		return new PropertyMatcherFactory<>(propertyName, extractor);
	}

    public static <T> PropertyMatcherFactory<T, Integer> intProperty(String propertyName, Function<T, Integer> extractor) {
        return new PropertyMatcherFactory<>(propertyName, extractor);
    }

	private PropertyMatcherFactory(String propertyName, Function<T, U> extractor) {
		this.extractor = extractor;
		this.propertyName = propertyName;
	}

	public FeatureMatcher<T, U> matches(Matcher<? super U> subMatcher) {
		return new FeatureMatcher<T, U>(subMatcher, propertyName, propertyName) {

			@Override
			protected U featureValueOf(T actual) {
				return extractor.apply(actual);
			}
		};
	}
	
	public FeatureMatcher<T, U> is(U expected) {
		return matches(Matchers.is(expected));
	}
}
