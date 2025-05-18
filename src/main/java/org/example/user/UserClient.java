package org.example.user;

public class UserClient {

    /**
     * Метод для получения токена пользователя после успешной регистрации или входа.
     * В реальной реализации выполнять запрос к API.
     */
    public String getToken(String email, String password) {
        // Простейшая проверка. При успешном вводе возвращаем тестовый токен.
        if (password != null && password.length() >= 6) {
            return "dummy-token-123456";
        }
        return null;
    }
}