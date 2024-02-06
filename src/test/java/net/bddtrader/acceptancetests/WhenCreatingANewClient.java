package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
@RunWith(SerenityRunner.class)
public class WhenCreatingANewClient {

    @Before
    public void setupBaseUrl() {
        baseURI = "http://localhost:9000/api";
    }

    @Test
    public void each_new_client_should_get_a_unique_id() {

        Map<String,Object> clientData = new HashMap<>();
        clientData.put("email","michael@scott.com");
        clientData.put("firstName","Michael");
        clientData.put("lastName","Scott");

        AuthenticationRequest.basic_authentication()
                .contentType(ContentType.JSON)
                .body(clientData)
                .when()
                .post("/client")
                .then().statusCode(200)
                .and().body("id", not(equalTo(0)))
                .and().body("email", equalTo("michael@scott.com"))
                .and().body("firstName", equalTo("Michael"))
                .and().body("lastName", equalTo("Scott"));
    }

    @Test
    public void a_new_client_can_be_created_using_a_map_structure() {

        Map<String,Object> clientData = new HashMap<>();
        clientData.put("email","kevin@malone.com");
        clientData.put("firstName","Kevin");
        clientData.put("lastName","Malone");

        AuthenticationRequest.digest_authentication().given().contentType(ContentType.JSON)
                .body(clientData)
                .when()
                .post("/client")
                .then().statusCode(200)
                .and().body("id", not(equalTo(0)))
                .and().body("email", equalTo("kevin@malone.com"))
                .and().body("firstName", equalTo("Kevin"))
                .and().body("lastName", equalTo("Malone"));
    }

}
