package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static net.serenitybdd.rest.SerenityRest.when;
import static org.assertj.core.api.Assertions.*;

public class WhenSetTheStockPrice {
    @Before
    public void prepare_rest_config() {
        RestAssured.baseURI = "http://localhost:9000/api/";
    }
    @Test
    public void should_set_the_stock_price() {
        RestAssured.given().contentType(ContentType.JSON)
                .and().body("345.8")
                .and().pathParam("stock", "aapl")
                .when().post("stock/{stock}/price");

                when().get("stock/{stock}/price", "aapl").then().statusCode(200);
                //.then().body("price", Matchers.equalTo(345.8));
                //OR
        String newPrice = SerenityRest.lastResponse().body().asString();
        assertThat(newPrice).isEqualTo("345.8");
    }
}
