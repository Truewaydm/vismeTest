package ui.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public final SelenideElement
            titleContent = $("div[class='auth-content'] h3"),
            emailField = $("input[type='email']"),
            passwordField = $("input[type='password']"),
            buttonLogin = $("button[type='submit']"),
            errorField = $(".error-message");

    @Step("Open logn page: {page}")
    public LoginPage open(String page) {
        return Selenide.open(page, this.getClass());
    }

    @Step("Check title: {text}")
    public LoginPage checkTitle(String text) {
        titleContent.shouldHave(Condition.exactText(text), Duration.ofSeconds(120));
        return this;
    }

    @Step("Login current user: {email}, {password}")
    public LoginPage signInCurrentUser(String email, String password) {
        emailField.setValue(email); // lowari8703@wiemei.com
        passwordField.setValue(password); // Qwerty321321qwerty
        buttonLogin.click();
        return this;
    }

    @Step("Check validation messages: {message}")
    public LoginPage checkValidationMessages(String message) {
        errorField.shouldHave(Condition.exactText(message), Duration.ofSeconds(60));
        return this;
    }
}
