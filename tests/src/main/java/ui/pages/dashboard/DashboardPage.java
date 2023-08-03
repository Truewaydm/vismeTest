package ui.pages.dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public final SelenideElement
            infoNameAndSurname = $("div[class='workspace-info'] div[class='name']");

    @Step
    public DashboardPage checkDashboardInfo(String nameAndSurname) {
        infoNameAndSurname.shouldHave(Condition.exactText(nameAndSurname), Duration.ofSeconds(120));
        return this;
    }
}
