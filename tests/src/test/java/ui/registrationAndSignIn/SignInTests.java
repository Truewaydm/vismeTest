package ui.registrationAndSignIn;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.baseTest.BaseTest;
import ui.DataProviders.Errors;
import ui.DataProviders.Titles;
import ui.DataProviders.Urls;
import ui.pages.dashboard.DashboardPage;
import ui.pages.login.LoginPage;

public class SignInTests extends BaseTest {

    public LoginPage loginPage = new LoginPage();
    public DashboardPage dashboardPage = new DashboardPage();
    public Urls url = new Urls();
    public Titles title = new Titles();
    public Errors error = new Errors();

    @BeforeEach
    void openAndCheckTitle() {
        loginPage
                .open(url.URL_LOGIN)
                .checkTitle(title.TITLE_LOG_IN);
    }

    @Epic("Sign In")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Authorization by registered verified user")
    @Test
    void authorizationByRegisteredVerifiedUser() {
        loginPage.signInCurrentUser(
                "lowari8703@wiemei.com",
                "Qwerty321321qwerty");
        dashboardPage.checkDashboardInfo("test test");
    }

    @Epic("Sign In")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Authorization by registered unconfirmed user")
    @Test
    void authorizationByRegisteredUnconfirmedUser() {
        loginPage
                .signInCurrentUser(
                        "yetrokakni@gufum.com",
                        "Qwerty321321qwerty")
                .checkValidationMessages(error.INVALID_EMAIL_AND_PASSWORD);
    }

    @Epic("Sign In")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Validating an empty email field")
    @Test
    void validationAnEmptyEmailField() {
        loginPage
                .signInCurrentUser(
                        "",
                        "Qwerty321321qwerty")
                .checkValidationMessages(error.EMAIL_REQUIRED);
    }

    @Epic("Sign In")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Validating an empty email field")
    @Test
    void validationAnEmptyPasswordField() {
        loginPage
                .signInCurrentUser(
                        "lowari8703@wiemei.com",
                        "")
                .checkValidationMessages(error.PASSWORD_REQUIRED);
    }
}
