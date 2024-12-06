package com.automationExcercise.auth;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RegisterTest {

    @Test
    public void TC_001(){
        given().baseUri("https://dummyjson.com/")
               .when().get("todos")
               .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("todos[0].id",equalTo(1));


    }


}
