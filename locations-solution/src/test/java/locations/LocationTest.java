package locations;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.awt.geom.Path2D.contains;
import static org.assertj.core.api.Assertions.tuple;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.startsWithIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LocationTest {





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
         void isOnEquatorTest2(RepetitionInfo repetitionInfo) {
            int index = repetitionInfo.getCurrentRepetition()-1;
            Location location = new Location("Quito",0,-78.5);
            assertEquals(values[index][1], location.isOnEquator());
        }


        @ParameterizedTest
        //@MethodSource("createLocation")
        @CsvFileSource(resources = "coordinates.csv")
        void testDistanceWithMethodSource(double lat1, double lon1, double lat2, double lon2, int expectedDist) {
            Location location1 = new Location("First",lat1,lon1);
            Location location2 = new Location("Second",lat2,lon2);
            double dist = location1.distanceFrom(location2);
            assertEquals(expectedDist, dist);
        }

    /*static Stream<Arguments> createLocation() {
        return Stream.of(
                arguments(51.5,0,47.497912,19.040235,1449000),
                arguments(47,17,0,170,141685000),
                arguments(47.497912,19.040235,0,-78.5,10611.89)
        );
    }*/

        @ParameterizedTest
        @MethodSource("createOnMeridianLocation")
        void testIsOnPrimeMeridianWithMethodSource(double lat, double lon, boolean expectedOnMeridian) {

        }

        static Stream<Arguments> createOnMeridianLocation() {
            return Stream.of(
                    arguments(51.5,0,true),
                    arguments(47,17,false),
                    arguments(47.497912,19.040235,false)
            );
        }

        @TestFactory
        Stream<DynamicTest> TestIsOnEquatorFavouriteSpaceWithExpectedValues(){
            return Stream
                    .of(new Object[][]{
                            {new Location("Quito",0,-78.5), true},
                            {new Location("Papua mellett", 0, 170), true}
                    })
                    .map(item -> DynamicTest.dynamicTest(
                            "favouriteSpace" + ((Location)item[0]).getName() + "lat" + ((Location)item[0]).getLat() +
                                    "lon" + ((Location)item[0]).getLon(),
                            () -> assertEquals(item[1], ((Location)item[0]).isOnEquator())));
        }




    @TestFactory
    Stream<DynamicTest> TestIsOnEquatorFavouriteSpace(){
        return Stream
                .of(new Location("Quito",0,-78.5), new Location("Papua mellett", 0, 170))
                .map(locations -> DynamicTest.dynamicTest(
                        "favouriteSpace" + locations.getName() + "lat" + locations.getLat() +
                                "lon" + locations.getLon(),() -> assertTrue(locations.isOnEquator())));
    }

    @Test
    void testReadLocations() {
        List<Location> locations = Arrays.asList(
                new Location("Quito",0,-78.5),
                new Location("Budapest",47.497912,19.040235),
                new Location("London",51.5,0),
                new Location("Papua mellett", 0, 170));


   /*    assertThat(List.of(locations),hasItem("Quito",0,-78.5));
        assertThat(List.of(locations),
                contains("Quito",0,-78.5, "Budapest", 47.497912,19.040235, "London",51.5,0, "Papua mellett", 0, 170));
        assertThat(List.of(locations),
                hasItem(hasProperty("name", startsWithIgnoringCase("Budapest"))));
        nem ismeri fel a hamcrest!
    }

    @Test
    void testReadLocations2()  {
            assertThat(locations)
                    .extracting("name", "lat", "lon")
                    .contains(tuple("Quito",0,-78.5),
                            tuple("Budapest", 47.497912,19.040235),
                            tuple("London",51.5,0),
                            tuple( "Papua mellett", 0, 170));

            assertThat(locations)
                    .filteredOn(l -> l.getlat().contains("0"))
                    .extracting(location -> location.getLat())
                    .containsOnly("Quito",0,-78.5);


        @Test
        void testLocationLatOrLonEqual0() {

            Condition<Location> latOrLon =
                    new Condition<>(l -> l.getLat().equals(0) || l -> l.getLon().equals(0),
                            "Lat or lon equal 0.");
            assertThat(Location).has(latOrLon);
        }*/
    }



}











