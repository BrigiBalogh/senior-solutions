package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private LocationParser locationParser;

    @BeforeEach
    // given
    void init() {
        locationParser = new LocationParser();

    }

    @Test
    void testParse() {

        // when: a tesztelendő metódus meghívása
        Location location = locationParser.parse("Budapest,47.497912,19.040235");
        Location city = locationParser.parse("Budapest,47.497912,19.040235");

        // then: ellenőrzés
        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat(),0.005);
        assertEquals(19.040235, location.getLon(), 0.005);
        assertNotSame(location,city);

    }

    @Test
    void testIsOnEquator() {
        assertAll("lat",
                () -> assertTrue(locationParser.parse("Quito,0,-78.5").isOnEquator()),
                () -> assertFalse(locationParser.parse("Budapest,47.497912,19.040235").isOnEquator()));

    }

    @Test
    void isOnEquatorTrue() {
        Location quito = locationParser.parse("Quito,0,-78.5");

        assertTrue(quito.isOnEquator());
    }

    @Test
    void isOnEquatorFalse() {
        Location budapest = locationParser.parse("Budapest,47.497912,19.040235");

        assertFalse(budapest.isOnEquator());
    }

    @Test
    void isOnPrimeMeridian() {
        assertAll("lon",
                () -> assertTrue(locationParser.parse("London,51.5,0").isOnPrimeMeridian()),
                () -> assertFalse(locationParser.parse("Budapest,47.497912,19.040235").isOnPrimeMeridian()));
    }

    @Test
    void testLonIsSloppyOrToSmall() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> new Location("Budapest",47.497912,-190.040235));
        assertEquals("Invalid location!", iae.getMessage());
    }

    private Object[][] values = {
            {new Location("Budapest",47.497912,-19.040235), false},
            {new Location("Quito",0,-78.5), true}
    };
    @RepeatedTest(2)
    public void isOnEquatorTest2(RepetitionInfo repetitionInfo) {
        int index = repetitionInfo.getCurrentRepetition()-1;
    }
}