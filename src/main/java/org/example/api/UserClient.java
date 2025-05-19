package org.example.api;

import org.example.api.models.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final Logger LOG = LogManager.getLogger(UserClient.class);
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";
    private static final String REGISTER_ENDPOINT = "/auth/register";
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String USER_ENDPOINT = "/auth/user";
    private static final String RESET_PASSWORD_ENDPOINT = "/auth/password-reset";

    /**
     * Регистрирует нового пользователя и возвращает токен
     */
    public String register(User user) {
        LOG.info("Registering new user: {}", user.getEmail());

        Response response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(BASE_URL + REGISTER_ENDPOINT);

        response.then().statusCode(200);

        String token = response.path("accessToken");
        LOG.debug("User {} registered successfully. Token received", user.getEmail());

        return token;
    }

    /**
     * Логин пользователя и возврат токена
     */
    public String login(User user) {
        LOG.info("Logging in user: {}", user.getEmail());

        Response response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(BASE_URL + LOGIN_ENDPOINT);

        response.then().statusCode(200);

        String token = response.path("accessToken");
        LOG.debug("User {} logged in successfully", user.getEmail());

        return token;
    }

    /**
     * Удаление пользователя по токену
     */
    public void delete(String accessToken) {
        if (accessToken == null) {
            LOG.warn("Attempt to delete user with null token");
            return;
        }

        LOG.info("Deleting user with token");

        given()
                .header("Authorization", accessToken)
                .delete(BASE_URL + USER_ENDPOINT)
                .then()
                .statusCode(202);

        LOG.debug("User deleted successfully");
    }

    /**
     * Обновление данных пользователя
     */
    public Response update(String accessToken, User newData) {
        LOG.info("Updating user data");

        Response response = given()
                .header("Authorization", accessToken)
                .contentType("application/json")
                .body(newData)
                .when()
                .patch(BASE_URL + USER_ENDPOINT);

        LOG.debug("Update request completed with status {}", response.getStatusCode());
        return response;
    }

    /**
     * Получение данных пользователя
     */
    public Response getUser(String accessToken) {
        LOG.debug("Getting user data");

        return given()
                .header("Authorization", accessToken)
                .when()
                .get(BASE_URL + USER_ENDPOINT);
    }

    /**
     * Запрос на сброс пароля
     */
    public Response requestPasswordReset(String email) {
        LOG.info("Requesting password reset for email: {}", email);

        return given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\"}")
                .when()
                .post(BASE_URL + RESET_PASSWORD_ENDPOINT);
    }

    /**
     * Полная процедура создания и получения токена пользователя
     */
    public String createUser(User user) {
        try {
            // Пытаемся сначала удалить пользователя, если он существует
            String token = login(user);
            delete(token);
        } catch (Exception e) {
            LOG.debug("User doesn't exist or couldn't be deleted: {}", e.getMessage());
        }

        return register(user);
    }
}