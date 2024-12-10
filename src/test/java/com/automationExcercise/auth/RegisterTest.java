package com.automationExcercise.auth;

import com.google.gson.Gson;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class RegisterTest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        private String name;
        private String email;
        private String password;
        private String title;
        private String birth_date;
        private String birth_month;
        private String birth_year;
        private String first_name;
        private String last_name;
        private String company;
        private String address1;
        private String address2;
        private String country;
        private String zipcode;
        private String state;
        private String city;
        private String mobile_number;
    }

    private static final String BASE_URI = "https://automationexercise.com/api/";

    @Test
    public void TC_001() {
        // Create User object
        User user = new User(
                "Ahmed Ghali",
                "ahmed.ghali@example.com",
                "StrongPassword123",
                "Mr",
                "07",
                "December",
                "1990",
                "Ahmed",
                "Ghali",
                "Tech Corp",
                "123 Downtown Street",
                "Apt. 4B",
                "Canada",
                "12345",
                "Cairo",
                "Cairo",
                "01012345678"
        );

        // Convert object to JSON
        Gson gson = new Gson();
        String requestBody = gson.toJson(user);

        // Send POST request and validate response
        Response response = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("createAccount")
                .then()
                .statusCode(200) // Check if the status code is 200
                .log().all()
                .extract()
                .response();

        // Validate response message
        String message = response.jsonPath().getString("message");
        assertEquals(message, "Account created successfully", "Validation message mismatch");
    }
}
