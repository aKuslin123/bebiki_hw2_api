package com.example.bebiki_hw2_api.api;

import com.example.bebiki_hw2_api.api.models.Unicorn;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class UnicornRequests {
    public static Unicorn createUnicorn(Unicorn unicorn) {

        String unicornJson = null;
        try {
            unicornJson = new ObjectMapper().writeValueAsString(unicorn);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return given()
                .body(unicornJson)
                .contentType(ContentType.JSON)
                .when()
                .post("/unicorns")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("$", hasKey("_id"))
                .extract().as(Unicorn.class, ObjectMapperType.GSON);
    }

    public static Unicorn updateUnicorn(String id, Unicorn unicorn) {
        String unicornJson;
        try {
            unicornJson = new ObjectMapper().writeValueAsString(unicorn);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return given()
                .body(unicornJson)
                .contentType(ContentType.JSON)
                .when()
                .put("/unicorns/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Unicorn.class, ObjectMapperType.GSON);
    }

    public static Unicorn getUnicorn(String id) {
        return given()
                .get("/unicorns/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Unicorn.class, ObjectMapperType.GSON);
    }

    public static void deleteUnicorn(String id) {
        given()
                .delete("/unicorns/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
