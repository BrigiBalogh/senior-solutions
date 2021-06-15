package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationNestedTest {

    private LocationParser locationParser;

    @BeforeEach
    void init() {
        locationParser = new LocationParser();
    }

    @Nested
    class  FavouriteSpace {


        @BeforeEach
        void createFavouriteSpace() {
            Location location = new Location("Kukutyin", 0, 0);
        }

        @Test
        void TestIsOnEquator() {
            Location kukutyin = locationParser.parse("Kukutyin, 0,0");
            assertTrue(kukutyin.isOnEquator());
        }

        @Test
        void isOnPrimeMeridian() {
            Location kukutyin = locationParser.parse("Kukutyin, 0,0");
            assertTrue(kukutyin.isOnPrimeMeridian());
        }
    }

    @Nested
    class anotherFavouriteSpace {

        @BeforeEach
        void createFavouriteSpace() {
            Location location = new Location("Budapest", 47.497912, 19.040235);
        }

        @Test
        void TestIsOnEquator() {
            Location budapest = locationParser.parse("Budapest,47.497912, 19.040235");
            assertFalse(budapest.isOnEquator());
        }

        @Test
        void isOnPrimeMeridian() {
            Location budapest = locationParser.parse("Budapest,47.497912, 19.040235");
            assertFalse(budapest.isOnPrimeMeridian());
        }
    }

}
