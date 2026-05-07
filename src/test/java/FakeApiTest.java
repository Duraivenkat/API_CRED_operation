import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;

public class FakeApiTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api.escuelajs.co/api/v1";
    }
    @Test
    public void testFilterPrduct() {
        RestAssured.given()
                .queryParam("price", 100)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("[0].price", Matchers.equalTo(100));
    }
    @Test
    public void categories()
    {
        RestAssured.given()
                .when().get("/categories")
                .then()
                .statusCode(200)
                .body("$",Matchers.instanceOf(List.class));
    }
    @Test
    public void categoriesWithid()
    {
        RestAssured.given()
                .pathParams("id",1)
                .when().get("/categories/{id}")
                .then()
                .statusCode(200)
                .body("id",Matchers.equalTo(1));
    }

    @Test
    public void testByFilter() {
        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }
}
