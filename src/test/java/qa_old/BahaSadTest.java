package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class BahaSadTest extends BaseTest {

    @Test
    public void testWestVirginiaCourt(){

        getDriver().get("http://www.courtswv.gov/");
        String title = getDriver().getTitle();
        String expectedTitle = "West Virginia Judiciary - Supreme Court of Appeals of West Virginia";
        Assert.assertEquals(title, expectedTitle);
        WebElement searchBar = getDriver().findElement(By.id("cludo-search-content-form-input"));
        WebElement searchButoon = getDriver().findElement(By.xpath("//button[contains(text(),'Search')]"));
        searchBar.sendKeys("Legal");
        searchButoon.click();
        String expectTitle = "West Virginia Judiciary";
        String actTitle = getDriver().getTitle();
        WebElement viewPDFFiles = getDriver().findElement(By.xpath("//a[@href='viewing-pdf-files.html']"));

        Assert.assertTrue(viewPDFFiles.isDisplayed());

        Assert.assertEquals(actTitle, expectTitle);

    }

}

