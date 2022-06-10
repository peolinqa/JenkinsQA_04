import com.google.common.base.Joiner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFreestyleProjectCyrillic08AlimDuiLTest extends BaseTest {

    @AfterMethod
    private void delProj() {
        getDriver()
                .findElement(
                        By.xpath("//span[contains(text(), 'Delete Project')]"))
                .click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void CreateFreestyleWithCyrillic(){

        String[] randomCyrillic = new String[9];
        for (int i = 0; i < randomCyrillic.length; i++) {
            randomCyrillic[i] = String.valueOf((char)(Math.random()*32 + 1072));
        }
        String expectedResult = Joiner.on("").join(randomCyrillic);

        getDriver()
                .findElement(
         By.xpath("//a[@href='/view/all/newJob']/span[@class='task-link-text']"))
                .click();
        getDriver()
                .findElement(
                        By.xpath("//input[@name='name']"))
                .sendKeys(randomCyrillic);
        getDriver()
                .findElement(
        By.xpath("//li[@class='hudson_model_FreeStyleProject']"))
                .click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 7);

        WebElement okButton = wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(
                 By.xpath("//button[@id='ok-button']")));
        okButton.click();

        getDriver()
                .findElement(
        By.xpath("//div[@class='tab config-section-activator config_post_build_actions']"))
                .click();

        WebElement saveButton = wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(
                 By.xpath("//span[@class='first-child']/button[@type='submit']")));
        saveButton.click();

        String actualResult = getDriver()
                .findElement(
                        By.xpath("//ul[@id='breadcrumbs']/li[3]"))
                .getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
