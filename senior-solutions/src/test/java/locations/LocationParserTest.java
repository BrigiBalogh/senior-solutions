package locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void testParse() {
        // given
        LocationParser locationParser = new LocationParser();

        // when: a tesztelendő metódus meghívása
        Location location = locationParser.parse("Budapest,47.497912,19.040235");

        // then: ellenőrzés
        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat(),0.005);
        assertEquals(19.040235, location.getLon(), 0.005);

    }
}