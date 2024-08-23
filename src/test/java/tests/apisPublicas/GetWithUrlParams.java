package tests.apisPublicas;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetWithUrlParams {
    @BeforeTest
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation("SSL");
    }
    @Test
    public void getWithUrlParams(){
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .formParam("id",4,5);
        Response response = request
                .when()
                .get();
        response.prettyPrint();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
    }
}
