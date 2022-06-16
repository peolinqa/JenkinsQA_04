import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class CheckDDMNewDomainTest extends BaseTest {

    private void preconditionGoToNewDomain () {
        getDriver().get("http://localhost:8080/credentials/store/system/newDomain");
    }

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
    public void testHelpForFeatureDomainHelpBeforeClick() {

        preconditionGoToNewDomain();

        WebElement help = getDriver().findElement(
                By.xpath("//a[@tooltip='Help for feature: Domain Name']"));

        Assert.assertEquals(help.getAttribute("title"), "Help for feature: Domain Name");
    }

    @Test
    public void testHelpForFeatureDomainHelpAfterClick() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("A short name to use for identifying this " +
                "credential domain. Credential domains are used to group " +
                "credentials together where they have elements of commonality, " +
                "e.g. where the same username/password combination is used " +
                "across multiple services/hosts.");
        expectedResult.add("A description for the domain, not used by Jenkins itself.");
        expectedResult.add("The list of specifications that define how to " +
                "identify requests for credentials as being valid for this " +
                "credential domain.");

        preconditionGoToNewDomain();

        Actions actions = new Actions(getDriver());
        WebElement helpDomainName = getDriver().findElement(
                By.xpath("//a[contains(@tooltip,'Domain Name')]"));
        actions.moveToElement(helpDomainName).click().perform();
        WebElement helpDescription = getDriver().findElement(
                By.xpath("//a[contains(@tooltip,'Description')]"));
        actions.moveToElement(helpDescription).click().perform();
        WebElement helpSpecification = getDriver().findElement(
               By.xpath("//a[contains(@tooltip,'Specification')]"));
        actions.moveToElement(helpSpecification).click().perform();

        List<String> actualResult = new ArrayList<>();
        actualResult.add(getDriver().findElement(
                By.xpath("//div[contains(text(),'A short name')]")).getText());
        actualResult.add(getDriver().findElement(
                By.xpath("//div[contains(text(),'A description')]")).getText());
        actualResult.add(getDriver().findElement(
                By.xpath("//div[contains(text(),'The " +
                        "list')]")).getText());

        Assert.assertEquals(actualResult, expectedResult);
    }
}