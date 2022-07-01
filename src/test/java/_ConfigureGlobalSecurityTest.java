import model.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.ArrayList;
import java.util.List;

public class _ConfigureGlobalSecurityTest extends BaseTest {
    private static final By SECURITY_CHAPTERS_CLASS_NAME = By.className("jenkins-section__header");

    private static final List<String> EXPECTED_CHAPTERS = List.of(
            "Authentication",
            "Security Realm",
            "Authorization",
            "Markup Formatter",
            "Agents",
            "CSRF Protection",
            "Hidden security warnings",
            "API Token",
            "SSH Server");

    private void enterConfigureGlobalSecurity() {
        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.ConfigureGlobalSecurity.click(getDriver());
    }

    @Test
    public void test9ChaptersDisplayedOnGlobalSecurityPage() {
        enterConfigureGlobalSecurity();

        List<WebElement> chapters = getDriver().findElements(SECURITY_CHAPTERS_CLASS_NAME);

        Assert.assertEquals(chapters.size(), 9);
    }

    @Test
    public void testCheckChapters() {
        enterConfigureGlobalSecurity();

        List<WebElement> chapters = getDriver()
                .findElements(SECURITY_CHAPTERS_CLASS_NAME);

        List<String> actualChapters = new ArrayList<>(chapters.size());
        for (WebElement e : chapters) {
            actualChapters.add(e.getText());
        }

        Assert.assertEquals(actualChapters, EXPECTED_CHAPTERS);
    }

    @Test
    public void testCheckHelpButtonSSHServer() {
        enterConfigureGlobalSecurity();

        WebElement locator = getDriver().findElement(
                By.xpath("//a[@title='Help for feature: SSHD Port']"));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", locator);

        getActions().pause(500).moveToElement(locator).perform();

        Assert.assertEquals(getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.id("tt"))).getText(),
                "Help for feature: SSHD Port");
    }

    @Test
    public void testCheckHelpButtonSSHServerPOM() {
        String tooltipHelpButtonSSHServer = new HomePage(getDriver())
                .clickManageJenkins()
                .clickConfigureGlobalSecurity()
                .getTextTooltipButtonHelpSSHServerPOM();

        Assert.assertEquals(tooltipHelpButtonSSHServer, "Help for feature: SSHD Port");
    }

}
