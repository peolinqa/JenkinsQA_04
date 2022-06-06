import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestRunner;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShurikCheckDDMNewDomainTest extends BaseTest {


    @Test
    public void testCheckDDMNewDomain() {
        String expectedResult
                = "http://localhost:8080/credentials/store/system/newDomain";

        getDriver().findElement(
                By.xpath("//span[contains(text(), 'Manage Jenkins')]")).click();
        getDriver().findElement(
                By.xpath("//a[@href='credentials']")).click();

        WebElement domainName = getDriver().findElement(
                By.xpath("//a[@href='/credentials/store/system']"));
        Actions action = new Actions(getDriver());
        action.moveToElement(domainName).perform();
        getDriver().findElement(By.id("menuSelector")).click();
        getDriver().findElement(
                By.xpath("//span[contains(text(), 'Add domain')]")).click();

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testHelpForFeatureDomainHelpBeforeClick() throws InterruptedException {

        String expectedResult = "Help for feature: Domain Name";

        getDriver().get("http://localhost:8080/credentials/store/system/newDomain");

        WebElement help = getDriver().findElement(
                By.xpath("//a[@tooltip='Help for feature: Domain Name']"));

        Assert.assertEquals(help.getAttribute("title"), expectedResult);
    }

    @Test
    public void testHelpForFeatureDomainHelpAfterClic() throws InterruptedException {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("A short name to use for identifying this " +
                "credential domain. Credential domains are used to group " +
                "credentials together where they have elements of commonality, " +
                "e.g. where the same username/password combination is used " +
                "across multiple services/hosts.");
        expectedResult.add("A description for the domain, not used by Jenkins itself.");

        getDriver().get("http://localhost:8080/credentials/store/system/newDomain");

        Actions actions = new Actions(getDriver());

        WebElement helpDomainName = getDriver().findElement(
                By.xpath("//a[@tooltip='Help for feature: Domain Name']"));
        actions.moveToElement(helpDomainName).click().build().perform();
        WebElement helpDescription = getDriver().findElement(
                By.xpath("//a[@tooltip='Help for feature: Description']"));
        actions.moveToElement(helpDescription).click().build().perform();

        List<String> actualResult = new ArrayList<>();
        actualResult.add(getDriver().findElement(
                By.xpath("//div[contains(text(),'A short name')]")).getText());
        actualResult.add(getDriver().findElement(
                By.xpath("//div[contains(text(),'A description')]")).getText());

        Assert.assertEquals(actualResult, expectedResult);
    }
}