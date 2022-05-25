import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ElviraKhTest extends BaseTest {

    @Test
    public void testCheckSearchWordInResults() {
        String searchWord = "ikea";
        By searchXPath = By.xpath("//div[@compid='searchSuggest']/descendant::input[not(@readonly)]");

        getDriver().get("https://houzz.com");

        WebElement inputSearch = getDriver().findElement(searchXPath);
        inputSearch.sendKeys(searchWord);
        inputSearch.sendKeys(Keys.ENTER);

        WebElement buttonGotIt = getDriver().findElement(By.xpath("//*[@id='hz-secondary-header']//button"));
        if (buttonGotIt.isDisplayed())
            buttonGotIt.click();

        String highLightText = getDriver().findElement(By.xpath("//a[starts-with(@compid,'popular-filter')]/span[1]")).getText();
        Assert.assertEquals(highLightText.substring(1, highLightText.length() - 1), searchWord);

        inputSearch = getDriver().findElement(searchXPath);
        Assert.assertEquals(inputSearch.getAttribute("value"), searchWord);

    }
}
