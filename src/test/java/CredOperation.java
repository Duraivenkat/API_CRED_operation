import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class CredOperation {
    private int id;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api.escuelajs.co/api/v1";
    }

    @Test(priority = 1)
    public void testCategories() {

        String name = "user_" + System.currentTimeMillis();
        String image = "https://google.com";

        Map<String, Object> body = Map.of(
                "name", name,
                "image", image
        );
        Response responce = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/categories");
        responce
                .then()
                .log().all()
                .statusCode(201)
                .body("name", Matchers.equalTo(name));
        id = responce.jsonPath().getInt("id");
    }
    @Test(priority = 2)
    public void testGetCatergories()
    {
        RestAssured.given()
                .pathParams("id",id)
                .when()
                .get("/categories/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("id",Matchers.equalTo(id));
    }
    @Test(priority = 3)
    public void testUpadate()
    {
        String name = "user_" + System.currentTimeMillis();
        String image = "https://google.com";

        Map<String, Object> body = Map.of(
                "name", name,
                "image", image
        );

        RestAssured.given()
                .pathParams("id",id);
    }
    @Test(priority = 4)
    public void testDeleteCatergories()
    {
        RestAssured.given()
                .pathParams("id",id)
                .when()
                .delete("/categories/{id}")
                .then()
                .log().all()
                .statusCode(200);
    }



}