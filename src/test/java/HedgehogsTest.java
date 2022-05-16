import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.TreeMap;

public class HedgehogsTest extends BaseTest {
    public static final String URL = "https://crossbrowsertesting.github.io/selenium_example_page.html";

    private static void clickSubmit(WebDriver driver) {
        driver.findElement(
                By.id("submitbtn")).click();
    }

    @Test
    public void testFillInputField() {
        final String HELLO = "Hello";
        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//form[@id='myform']/input[@name='text']"))
                .sendKeys(HELLO + Keys.ENTER);

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='form-results']/div[1]/span[not(contains(text(),'text'))]")).getText();

        Assert.assertEquals(actualResult, HELLO);
    }

    @Test
    public void testClickCheckbox() {
        getDriver().get(URL);
        getDriver().findElement(
                        By.name("checkbox")).click();
        clickSubmit(getDriver());

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='form-results']/div[1]/span[contains(text(),'on')]")).getText();

        Assert.assertEquals(actualResult, "on");
    }

    @Test
    public void testSelectMenu() {
        getDriver().get(URL);
        WebElement select = getDriver().findElement(
                By.id("dropdown"));
        Select dropdown = new Select(select);
        dropdown.selectByVisibleText("Option 4");
        clickSubmit(getDriver());

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='form-results']/div[1]/span[contains(text(),'option4')]")).getText();

        Assert.assertEquals(actualResult, "option4");
    }

    @Test
    public void testExitButton() {
        getDriver().get(URL);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(
                By.xpath("//h4"))).perform();
       
        getDriver().findElement(
                By.xpath("//div[@id='pop-up-page']/button[@onclick='popup()']"))
                .click();
    }
}
