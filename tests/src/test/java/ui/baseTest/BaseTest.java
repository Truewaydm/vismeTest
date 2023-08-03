package ui.baseTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import ui.extensions.TestLoggingListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.utils.PropertyLoader;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ExtendWith({AllureJunit5.class, ScreenShooterExtension.class, TextReportExtension.class, TestLoggingListener.class})
public class BaseTest {

    public static final PropertyLoader propertyLoader = PropertyLoader.getInstance();
    private static final String HEADLESS_TYPE = propertyLoader.getHeadlessType();

    //Local screenshot and source page
    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true);

    public static void setUpBrowser() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        //1-Allow, 2-Block, 0-default
        prefs.put("profile.default_content_setting_values.notifications", 1);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("disable-infobars");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        switch (HEADLESS_TYPE) {
            case "true":
                options.addArguments("--headless");
                log.info("Launch with HEADLESS_TYPE: " + HEADLESS_TYPE);
                break;
            case "false":
                log.info("Launch with HEADLESS_TYPE: " + HEADLESS_TYPE);
                break;
            default:
                Assertions.fail("Didn't launch with any HEADLESS_TYPE: " + HEADLESS_TYPE);
                break;
        }
        WebDriverRunner.setWebDriver(new ChromeDriver(options));
    }

    @Step("Set up Browser options and AllureSelenide listener")
    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().browserVersion("115").setup();
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.baseUrl = propertyLoader.getBaseUrl();
        Configuration.reopenBrowserOnFail = true;
        setUpBrowser();
        //screenshots work if test to be failed
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @Step("Close WebDriver and AllureSelenide listener")
    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allureSelenide");
        Selenide.closeWebDriver();
    }
}
