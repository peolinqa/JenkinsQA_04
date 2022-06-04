import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class BahaMTest extends BaseTest {

    static String newItemButton =  "//div[@id='tasks']/div[1]/span/a/span[2]"; //xpath
    static String freestyleProjectButton =  "//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span"; //xpath
    static String okButton =  "ok-button"; //id
    static String saveButton =  "//div[@class='bottom-sticker-inner']/span/span/button"; //xpath
    static String dashboardMenu =  "//ul[@id='breadcrumbs']/li[1]/a"; //xpath
    static String itemNameField =  "name"; //id
    static String itemObjectName =  "//tr[@class=' job-status-nobuilt']//a[@href='job/freestyle-project-()+-_%7E-1/']"; //xpath

    static String itemNameInvalid_message =  "itemname-invalid"; //id
    static String ErrorMessage =  "//div[@id=\"main-panel\"]/h1"; //xpath
    static String unsafeErrorMessage =  "//div[@id='main-panel']/p"; //xpath

    public void clickOkButton() {
        getDriver().findElement(By.id(okButton)).click();
    }


    @Test
    public void TC_001_001_newFreestyleItem() {

        getDriver().findElement(By.xpath(newItemButton)).click();

        getDriver().findElement(By.id(itemNameField)).
                sendKeys("freestyle-project-()+-_~-1");
        getDriver().findElement(By.xpath(freestyleProjectButton)).click();
        clickOkButton();
        getDriver().findElement(By.xpath(saveButton)).click();

        getDriver().findElement(By.xpath(dashboardMenu)).click();
        Assert.assertTrue(getDriver().findElement(By.xpath(itemObjectName)).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath(itemObjectName)).getText(),
                "freestyle\n" + "-project\n" +"-()+-_~-1");
    }

    @Ignore
    @Test
    public void TC_001_002_newFreestyleItem_negative() {

        getDriver().findElement(By.xpath(newItemButton)).click();

        getDriver().findElement(By.id(itemNameField)).sendKeys("freestyle-project-2!");
        Assert.assertEquals(getDriver().findElement(By.id(itemNameInvalid_message)).getText(),"» ‘!’ is an unsafe character");

        getDriver().findElement(By.xpath(freestyleProjectButton)).click();
        clickOkButton();
        Assert.assertEquals(getDriver().findElement(By.xpath(ErrorMessage)).getText(),
                "Error");
        Assert.assertTrue(getDriver().findElement(By.xpath(unsafeErrorMessage)).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath(unsafeErrorMessage)).getText(),
                "‘!’ is an unsafe character");
    }

}