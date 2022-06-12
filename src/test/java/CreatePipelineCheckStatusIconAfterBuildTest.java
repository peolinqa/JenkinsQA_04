import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreatePipelineCheckStatusIconAfterBuildTest extends BaseTest {
    private static final String NAME = RandomStringUtils.randomAlphanumeric(4, 8);

    @Test(description = "TC_017.001")
    public void testCheckIcon() {
        $("[title='New Item']").click();
        $("#name").sendKeys(NAME);
        $x("//li[contains(@class,'WorkflowJob')]").click();
        $("#ok-button").click();
        $("#cb16 ~ label").click();
        new Select($(".samples select")).selectByValue("hello");
        $("[type='submit']").click();
        $("#jenkins-home-link").click();
        $x(String.format("//span[contains(text(), '%s')]/following-sibling::*[name()='svg']", NAME)).click();
        String iconLocator = String.format("//tr[@id='job_%s']/td/div/span/*[@tooltip]", NAME);
        while ($x(iconLocator).getAttribute("tooltip").equals("In progress")
                || $x(iconLocator).getAttribute("tooltip").equals("Not built")) {
            getDriver().navigate().refresh();
        }
        Assert.assertEquals($x(iconLocator).getAttribute("tooltip"), "Success");
    }

    private WebElement $(String locator) {
        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(locator))));
    }

    private WebElement $x(String locator) {
        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(locator))));
    }
}

