package com.qaCart.testCases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.qaCart.testCases.pojo.loginPojo;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.ldap.HasControls;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testInfo {
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
                .headers(infoHeaders).log().all()
        .when().get("api/v1/info/courses")
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


        given()
                .spec(req)
                .body(user)
                .when()
                .post("api/v1/students/login")
                .then().log().all();
    }
}
