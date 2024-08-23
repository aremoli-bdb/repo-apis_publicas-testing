package tests.apisPublicas;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetSingleObject {
    @BeforeTest
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation("SSL");
    }

    @Parameters({"id","expectedStatusCode"})
    @Test
        public void getSingleObjetc(String id, int expectedStatusCode){
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects");
        Response response = request
                .when()
                .get(id);

        response.prettyPrint();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,expectedStatusCode);


//        JSONObject jsonResponse = new JSONObject(response.asString());
//        String name = jsonResponse.getString("name");
//        System.out.println(name);
//
//        JSONObject jasonDato = jsonResponse.getJSONObject("data");
//        String cpuModel = jasonDato.getString("CPU model");
//        System.out.println(cpuModel);

    }
}
