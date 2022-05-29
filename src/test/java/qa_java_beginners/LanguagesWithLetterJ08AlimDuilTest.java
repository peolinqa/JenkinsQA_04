package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class LanguagesWithLetterJ08AlimDuilTest extends BaseTest {

    @Test
    public void testlanguagesWithLetterJ() {

        String expectedResult = "All languages starting with the letter J " +
                "are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver()
                .findElement(
                        By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"))
                .click();
        getDriver()
                .findElement(
                        By.xpath("//ul[@id='submenu']/li/a[@href='j.html']"))
                .click();
        WebElement languagesWithLetterJ = getDriver().findElement(
                By.xpath("//div[@id='main']/p/strong"));

        String actualResult = languagesWithLetterJ.getText();

        if (languagesWithLetterJ.getCssValue("font-weight") == "bold") {
            Assert.assertEquals(actualResult, expectedResult);
        }
    }
}
