import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class AnnFedorovaTargetTest extends BaseTest {
    @Test
    public void searchKidsApparel () {
        getDriver().get("https://www.target.com/");
        final String KIDSAPPAREL = "kids apparel";
        WebElement searchBox = getDriver().findElement(By.xpath("//*[@id" +
                "='search']"));
        WebElement searchButton = getDriver().findElement(By.xpath("//button" +
                "[@aria-label='go']"));

        searchBox.sendKeys(KIDSAPPAREL);
        searchButton.click();

        getDriver().manage().timeouts().implicitlyWait(10000,
                TimeUnit.MILLISECONDS);

        WebElement searchTitle = getDriver().findElement
                (By.xpath("//div[@class='styles__FacetsStyledContainer-sc-" +
                        "y0hm-0 iHGdCe h-margin-b-default']//h2"));
        String actualResult =
                searchTitle.getText().substring(searchTitle.getText().length()
                                - KIDSAPPAREL.length() - 1,
                        searchTitle.getText().length() - 1);

        Assert.assertEquals(actualResult, KIDSAPPAREL);
    }
}