package tests.apisPublicas;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class UpdateObject {
    @BeforeTest
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation("SSL");
   }
    @Test
    public void updateObject(){
        File postBody = new File("src/main/resources/updateObject.json");
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .header("Content-Type","application/json")
                .body(postBody);
        Response response = request
                .when()
                .put("/ff80818190966d7f01909d87110a157a");
        response.prettyPrint();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
    }
}
