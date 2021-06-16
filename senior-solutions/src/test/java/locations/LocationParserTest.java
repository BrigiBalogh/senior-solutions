package locations;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
            {new Location("London",51.5,0), false},
            {new Location("Quito",0,-78.5), true}
    };
    @RepeatedTest(3)
    public void isOnEquatorTest2(RepetitionInfo repetitionInfo) {
        int index = repetitionInfo.getCurrentRepetition()-1;
        Location location = new Location("Quito",0,-78.5);
        assertEquals(values[index][1], location.isOnEquator());
    }


    @ParameterizedTest
    @MethodSource("createLocation")
    void testIsOnPrimeMeridianWithMethodSource(double lat, double lon) {
        Location location = new Location("London",51.5,0);
        assertEquals(lon,location.isOnPrimeMeridian());
    }

    static Stream<Arguments>createLocation() {
        return Stream.of(
                arguments(51.5,0,47.497912,19.040235,1449000),
                arguments(47,17,0,170,141685000),
                arguments(47.497912,19.040235,0,-78.5,10611.89)
        );
    }

    @TestFactory
    Stream<DynamicTest> TestIsOnEquatorFavouriteSpace(){
        return Stream
                .of(new Location[][] {"Quito",0,-78.5}, {"Papua mellett", 0, 170})
                 .map(locations -> DynamicTest.dynamicTest(
                         "favouriteSpace" + locations[0] + "lat" + locations[1] +
                                 "lon" + locations[2],
                         assertEquals(locations[1], new Location("Quito",0,-78.5)
                                 .getLat(locations[1]))));
    }
}
