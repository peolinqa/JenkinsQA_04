import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Date;
import java.util.List;

public class KICreatePipelineTest extends BaseTest {
    private static final By NEW_ITEM = By.cssSelector("[title='New Item']");
    private static final By INPUT_LINE = By.id("name");
    private static final By PIPELINE = By.xpath("//span[text()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private final String NAME_INPUT = "test123";
    private Date date;

    @BeforeMethod
    public void setUp() {
        date = new Date();
    }

    private void fillNameAndClick() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE).sendKeys(NAME_INPUT);
        getDriver().findElement(PIPELINE).click();
    }

    private void fillRandomNameAndClick() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE)
                .sendKeys(NAME_INPUT + date.getTime());
        getDriver().findElement(PIPELINE).click();
    }

    @Test(description = "TC_017.003")
    public void testCheckValidationItemName() {
        fillNameAndClick();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
        getDriver().findElement(By.xpath("//li//a[@href='/']")).click();
        fillNameAndClick();
        String errorMessage = getDriver().findElement(
                By.id("itemname-invalid")).getText();

        getDriver().findElement(OK_BUTTON).click();
        String errorMessageTwo = getDriver().findElement(
                By.xpath("//h1")).getText();

        Assert.assertEquals(errorMessage,
                "» A job already exists with the name ‘test123’");
        Assert.assertEquals(errorMessageTwo, "Error");
    }

    @Ignore
    @Test(description = "TC_017.009")
    public void testCheckDropDownMenuPipeline() {
        fillRandomNameAndClick();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.cssSelector(
                "[class='tab config-section-activator config_pipeline']"))
                .click();

        List<WebElement> optionsDropDown = getDriver().findElements(
                By.xpath("//div[1][@class='samples']//select/option"));

        Assert.assertEquals(optionsDropDown.size(), 4);
    }
}
