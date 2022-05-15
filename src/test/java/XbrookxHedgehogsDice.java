import runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class XbrookxHedgehogsDice extends BaseTest {


    @Test
    public void testSearchBox() throws InterruptedException {
        getDriver().get("https://www.dice.com/");

        WebElement searchBoxJob = getDriver().findElement(By.id("typeaheadInput"));
        WebElement searchBoxCity = getDriver().findElement(By.id("google-location-search"));
        WebElement searchButton = getDriver().findElement(By.id("submitSearch-button"));


        searchBoxJob.sendKeys("quality assurance");
        searchBoxCity.sendKeys("California, USA");

        searchButton.click();

        Thread.sleep(1000); //

        WebElement searchBoxResult = getDriver().findElement(By.id("saveAsNewInput"));
        searchBoxJob = getDriver().findElement(By.id("typeaheadInput"));
        searchBoxCity = getDriver().findElement(By.id("google-location-search"));
        searchBoxResult = getDriver().findElement(By.id("saveAsNewInput"));

        Assert.assertEquals(searchBoxJob.getAttribute("value"), "quality assurance");
        Assert.assertEquals(searchBoxCity.getAttribute("value"), "California, USA");
        Assert.assertEquals(searchBoxResult.getAttribute("value"), "quality assurance + California, USA");

        getDriver().quit();

    }


}

