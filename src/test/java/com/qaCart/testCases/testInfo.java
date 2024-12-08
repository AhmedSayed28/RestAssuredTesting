package com.qaCart.testCases;

import com.google.gson.Gson;
import com.qaCart.testCases.pojo.loginPojo;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.Test;

import javax.naming.ldap.HasControls;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testInfo {




    @Test
    public void getCoursesInfo(){

        HashMap<String,String> infoHeaders = new HashMap<>();
        infoHeaders.put("type","WEB");
        infoHeaders.put("language","JAVA");

//        Header typeHeader = new Header("type","WEB");
//        Header langHeader = new Header("language","JAVA");
//
//        Headers infoHeaders = new Headers(typeHeader,langHeader);

        given()
                .baseUri("https://todo.qacart.com/")
                .headers(infoHeaders).log().all()
        .when().get("api/v1/info/courses")
        .then().log().ifError()
                .assertThat().statusCode(200).
                assertThat().body("count",equalTo(1));
    }

    @Test
    public void loginTest(){
        loginPojo user = new loginPojo("hatem@example.com","123456");


        given()
                .baseUri("https://todo.qacart.com/")
                .header("Content-Type","application/json")
                .body(user)
                .when()
                .get("api/v1/students/login")
                .then().log().all();
    }
}
