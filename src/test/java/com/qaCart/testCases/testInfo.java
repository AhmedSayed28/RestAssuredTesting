package com.qaCart.testCases;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import javax.naming.ldap.HasControls;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

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
                .assertThat().statusCode(200).log().body();
    }
}
