import com.google.common.base.Joiner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RenameFolderWithSpecChar08AlimDuiLTest extends BaseTest {

    @AfterMethod
    private void delFolder() {
        getDriver()
                .findElement(
                        By.xpath("//ul[@id='breadcrumbs']/li[3]"))
                .click();
        getDriver()
                .findElement(
                        By.xpath("//a[@title='Delete Folder']"))
                .click();
        getDriver()
                .findElement(
                        By.xpath("//button[@type='submit']"))
                .click();
    }

    @Test
    public void CreateFreestyleWithCyrillic() {

        String[] randomLatinCharacters = new String[9];
        for (int i = 0; i < randomLatinCharacters.length; i++) {
            randomLatinCharacters[i] = String.valueOf((char) (Math.random() * 26 + 97));
        }

        String expectedResult = "Error";
        String expectedResultName = Joiner.on("").join(randomLatinCharacters);


        getDriver()
                .findElement(
                        By.linkText("New Item"))
                .click();
        getDriver()
                .findElement(
                        By.id("name"))
                .sendKeys(randomLatinCharacters);
        getDriver()
                .findElement(
                        By.xpath("//div[@id='j-add-item-type-nested-projects']//li[1]"))
                .click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 7);

        WebElement okButton = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                By.id("ok-button")));
        okButton.click();

        WebElement saveButton = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                By.xpath("//button[@type='submit']")));
        saveButton.click();
        getDriver()
                .findElement(
                        By.xpath("//a[@title='Rename']"))
                .click();

        String[] specialCharacters =
                {"!", "@", "#", "$", "%", "^", "&", "*", "|", "/", ".", ">", "<"};
        for (int i = 0; i < specialCharacters.length; i++) {
            getDriver()
                    .findElement(
                            By.xpath("//input[@checkdependson='newName']"))
                    .clear();
            getDriver()
                    .findElement(
                            By.xpath("//input[@checkdependson='newName']"))
                    .sendKeys(specialCharacters[i]);
            getDriver()
                    .findElement(
                            By.id("yui-gen1-button"))
                    .click();

            String actualResult = getDriver()
                    .findElement(
                            By.xpath("//h1"))
                    .getText();
            Assert.assertEquals(actualResult, expectedResult);

            getDriver()
                    .findElement(
                            By.xpath("//ul[@id='breadcrumbs']/li[3]"))
                    .click();
            getDriver()
                    .findElement(
                            By.xpath("//a[@title='Rename']"))
                    .click();
        }

        String actualResultName = getDriver()
                .findElement(
                        By.xpath("//ul[@id='breadcrumbs']/li[3]"))
                .getText();

        Assert.assertEquals(actualResultName, expectedResultName);
    }
}
