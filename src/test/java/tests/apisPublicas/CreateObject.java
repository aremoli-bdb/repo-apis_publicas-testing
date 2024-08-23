package tests.apisPublicas;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class CreateObject {

    @BeforeTest
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation("SSL");

    }
    String path="./Reporte/Apis.html";
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter(path);
    ExtentTest test;

    @Test
    public void createObject() {
//        try {
            extent.attachReporter(spark);
            test = extent.createTest("Prueba");
            File postBody = new File("src/main/resources/createObjetc.json");
            test.log(Status.INFO, "Paso la ruta del archivo del Body que es: " + postBody);
            RequestSpecification request = given()
                    .baseUri("https://api.restful-api.dev")
                    .basePath("/objects")
                    .header("Content-Type", "application/json")
                    .body(postBody);
            Response response = request
                    .when()
                    .post();
            test.log(Status.INFO, "Se realizo el consumo del Api");
            response.prettyPrint();
            int statusCode = response.statusCode();
            test.log(Status.INFO, "El StatusCode es: " + statusCode);
            Assert.assertEquals(statusCode, 200);
//        }catch (Exception e){
//            System.out.println("Error:  "+e);
//        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE ){
            test.log(Status.FAIL,"El test fallo");
        }else{
            test.log(Status.PASS, "Fue exitoso el Test");
            }
        extent.flush();
    }
}
