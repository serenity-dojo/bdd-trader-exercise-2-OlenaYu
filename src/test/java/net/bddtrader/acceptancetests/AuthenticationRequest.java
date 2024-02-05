package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class AuthenticationRequest {
    public static RequestSpecification basic_authentication() {
        return given().auth().basic("user", "password");
    }
    public static RequestSpecification digest_authentication() {
        return given().auth().digest("user", "password");
    }

}
