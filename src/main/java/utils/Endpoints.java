package utils;

public class Endpoints {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER = BASE_URL + "/register";
    public static final String FORGOT_PASSWORD = BASE_URL + "/forgot-password";
    public static final String USER_REGISTER = BASE_URL + "/api/auth/register"; // API для регистрации пользователя
    public static final String USER_LOGIN = BASE_URL + "/api/auth/login";   // API для авторизации пользователя
    public static final String USER_DELETE = BASE_URL + "/api/auth/user";    // API для удаления пользователя
}