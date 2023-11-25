import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class WithLambdaStepsTest extends TestBase {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 84;

    @Test
    public void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open("https://github.com");
        });
        step("Search repository " + REPOSITORY, () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });

        step("Click repo link " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Open tab Issues", () -> {
            $("#issues-tab").click();
        });
        step("Check Issue tab number " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void annotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);

    }

}

