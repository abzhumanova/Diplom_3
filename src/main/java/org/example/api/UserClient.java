package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.models.User;
import static io.restassured.http.ContentType.JSON;

public class UserClient {
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/api/auth";

    /** Создаёт пользователя, возвращает accessToken */
    public String create(User user) {
        Response resp = RestAssured.given()
                .contentType(JSON)
                .body(user)
                .post(baseUrl + "/register");
        resp.then().statusCode(200);
        return resp.jsonPath().getString("accessToken");
    }

    /** Удаляет пользователя по токену */
    public void delete(String accessToken) {
        if (accessToken == null) return;
        RestAssured.given()
                .header("Authorization", accessToken)
                .delete(baseUrl + "/user")
                .then()
                .statusCode(202);
    }
}