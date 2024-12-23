package com.qaCart.testCases;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaCart.testCases.pojo.loginPojo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Listeners(com.utils.ExtentReportManager.class)
public class TestInfo {
    RequestSpecification req;
    @BeforeClass
    public void before(){
        req = given()
                .baseUri("https://todo.qacart.com/")
                .header("Content-Type","application/json");
    }

    @Test
    public void getCoursesInfo(){

        HashMap<String,String> infoHeaders = new HashMap<>();
        infoHeaders.put("type","WEB");
        infoHeaders.put("language","JAVA");


        given()
                .spec(req)
                .headers(infoHeaders)
        .when().get("api/v1/infoo/courses")
        .then().log().ifError()
                .assertThat().statusCode(200).
                assertThat().body("count",equalTo(1));
    }

    @Test
    public void loginTest() throws IOException {
        File data = new File("src/test/java/com/qaCart/testCases/data/userData.json");

        ObjectMapper mapper = new ObjectMapper();
        loginPojo user = mapper.readValue(data,loginPojo.class);
        System.out.println(user);


        Response res = given()
                .spec(req)
                .body(user)
                .when()
                .post("api/v1/students/login")
                .then().extract().response();

        String resp = JsonPath.from(res.asString()).getString("message");
        System.out.println(resp);
    }
}
