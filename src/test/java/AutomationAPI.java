import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

public class AutomationAPI {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Test(priority = 1)
    public void testCase01() {

        RestAssured.given()
                .when()
                .get("/productsList")
                .then()
                .log().all()
                .statusCode(200)
                .body("products.size()", Matchers.greaterThan(0));
    }
    @Test(priority = 2)
    public void testPostProductsList_shouldReturn405() {

        RestAssured.given()
                .when()
                .post("/productsList")
                .then()
                .log().all()
                .statusCode(405)
//                .body("responseCode",Matchers.equalTo(405))
                .body(containsString("This request method is not supported."));
    }
}