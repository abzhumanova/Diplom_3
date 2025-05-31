package models;

import com.google.gson.annotations.SerializedName;

public class LoginCredentialsModel {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginCredentialsModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}