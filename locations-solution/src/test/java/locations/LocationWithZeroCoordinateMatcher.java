package locations;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


    public class LocationWithZeroCoordinateMatcher extends TypeSafeMatcher<Location> {

        public static Matcher<Location> LocationWithZeroCoordinate(Matcher<String> matcher) {
            return new locations.LocationWithZeroCoordinateMatcher(matcher);
        }

        private Matcher<String> matcher;

        public LocationWithZeroCoordinateMatcher (Matcher<String> matcher) {
            this.matcher = matcher;
        }

        @Override
        protected boolean matchesSafely(Location item) {
            return matcher.matches(item.getLat());
        }

        @Override
        protected void describeMismatchSafely(Location item, Description mismatchDescription) {
            mismatchDescription
                    .appendText(" location with lat ")
                    .appendValue(item.getName());
        }

        @Override
        public void describeTo(Description description) {
            description
                    .appendText(" Location with lat ")
                    .appendDescriptionOf(matcher);
        }


    }


