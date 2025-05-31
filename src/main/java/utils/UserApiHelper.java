package utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.LoginCredentialsModel;
import models.UserRegisterModel;
import com.google.gson.Gson;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

public class UserApiHelper {

    private static final String CREATE_USER = Endpoints.USER_REGISTER;
    private static final String LOGIN_USER = Endpoints.USER_LOGIN;
    private static final String DELETE_USER = Endpoints.USER_DELETE;

    @Step("Создание пользователя через API")
    public void createUser(String email, String password, String name) {
        UserRegisterModel userRegister = new UserRegisterModel(email, password, name); // Создаем объект UserRegisterModel
        Gson gson = new Gson(); // Создаем экземпляр Gson
        String requestBody = gson.toJson(userRegister); // Сериализуем объект в JSON

        given()
                .baseUri(Endpoints.BASE_URL)
                .header("Content-Type", "application/json")
                .body(requestBody) // Используем сериализованный JSON
                .when()
                .post(CREATE_USER)
                .then().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(String email, String password) {
        String token = getAccessToken(email, password);
        given()
                .baseUri(Endpoints.BASE_URL)
                .header("Authorization", token)
                .when()
                .delete(DELETE_USER)
                .then().statusCode(HttpURLConnection.HTTP_ACCEPTED);
    }

    @Step("Получение accessToken через API")
    public String getAccessToken(String email, String password) {
        LoginCredentialsModel loginCredentials = new LoginCredentialsModel(email, password); // Создаем LoginCredentialsModel
        Gson gson = new Gson();
        String requestBody = gson.toJson(loginCredentials);

        Response response = given()
                .baseUri(Endpoints.BASE_URL)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(LOGIN_USER)
                .then().statusCode(HttpURLConnection.HTTP_OK)
                .extract().response();

        return response.path("accessToken");
    }
}