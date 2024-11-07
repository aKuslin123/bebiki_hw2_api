package com.example.bebiki_hw2_api;

import com.example.bebiki_hw2_api.api.UnicornRequests;
import com.example.bebiki_hw2_api.api.models.Unicorn;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {
    //https://crudcrud.com/

    @BeforeAll
    public static void setupTests() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://crudcrud.com/api/1e29455b62ba40be98215956c402d858";
    }

    @Test
    public void userShouldBeAbleCreateUnicorn() {
        Unicorn unicorn = Unicorn.builder()
                .name("Sparkle Angel")
                .colourTail("blue")
                .build();

        Unicorn createdUnicorn = UnicornRequests.createUnicorn(unicorn);

        UnicornRequests.getUnicorn(createdUnicorn.getId());
    }

    @Test
    public void userShouldBeAbleDeleteExistingUnicorn() {
        Unicorn unicorn = Unicorn.builder()
                .name("Sparkle Angel")
                .colourTail("blue")
                .build();

        Unicorn createdUnicorn = UnicornRequests.createUnicorn(unicorn);

        UnicornRequests.deleteUnicorn(createdUnicorn.getId());

        given()
                .get("/unicorns/" + createdUnicorn.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void userShouldBeAbleUpdateUnicornsTail() {
        Unicorn unicorn = Unicorn.builder()
                .name("Sparkle Angel")
                .colourTail("blue")
                .build();

        Unicorn createdUnicorn = UnicornRequests.createUnicorn(unicorn);
        createdUnicorn = UnicornRequests.getUnicorn(createdUnicorn.getId());
        assertEquals("blue", createdUnicorn.getColourTail());

        createdUnicorn.setColourTail("Red");
        UnicornRequests.updateUnicorn(createdUnicorn.getId(), createdUnicorn);

        Unicorn updatedUnicorn = UnicornRequests.getUnicorn(createdUnicorn.getId());
        assertEquals("Red", updatedUnicorn.getColourTail());

        UnicornRequests.deleteUnicorn(createdUnicorn.getId());

        given()
                .get("/unicorns/" + createdUnicorn.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
