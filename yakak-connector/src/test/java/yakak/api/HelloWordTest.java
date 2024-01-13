package yakak.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class HelloWordTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/v1/status")
          .then()
             .statusCode(200);
    }

}