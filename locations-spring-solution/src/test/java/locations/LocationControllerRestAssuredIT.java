package locations;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerRestAssuredIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocationsService locationsService;



    @BeforeEach
    void init() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.requestSpecification =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON);

        locationsService.deleteAll();
    }

    @Test
    void testGetLocations() {
        with()
                .body(new CreateLocationCommand("Budapest",47.497912,19.040235))
                .post("/location")
                .then()
                .statusCode(201)
                .body("name", equalTo("Budapest"))
                .log();

        with()
                .get("/location")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("Budapest"))
                .body("size()", equalTo(3));
    }

    @Test
    void testGetLocationById() {
        with()
                .body(new CreateLocationCommand("Budapest",47.497912,19.040235))
                .post("/location/id/1")
                .then()
                .statusCode(201)
                .body("name", equalTo("Budapest"))
                .log();

        with()
                .get("/location")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("Budapest"));

    }

    @Test
    void testCreateLocation() {
        with()
                .body(new CreateLocationCommand("Budapest", 47.497912, 19.040235))
                .post("/location")
                .then()
                .statusCode(201)
                .body("name", equalTo("Budapest"))
                .log();
    }


    @Test
    void validate() {
        with()
                .body(new CreateLocationCommand("Budapest", 47.497912, 19.040235))
                .post("/locations")
                .then()
                .body(matchesJsonSchemaInClasspath("location-dto.json"));
    }

}

