import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest extends TestBase {
    @BeforeEach
    public void setUp() {
        open("https://github.com");

    }

    @Test
    public void issueSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());


        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("Issue_created_to_test_allure_reports")).should(Condition.exist);
    }

}

