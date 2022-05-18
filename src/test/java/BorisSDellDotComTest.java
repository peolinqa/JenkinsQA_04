import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

@Ignore
public class BorisSDellDotComTest extends BaseTest {
    private static final String EXPECTED_PRICE = "$2,899.00";

    @BeforeMethod
    @Override
    protected void beforeMethod() {
        super.beforeMethod();
        getDriver().get("https://www.dell.com");
        getDriver().manage().deleteAllCookies();
    }

    @Test
    public void testSearchForLaptop() {
        WebElement searchBox = getDriver().findElement(By.id("mh-search-input"));
        searchBox.sendKeys("xps 15");
        searchBox.sendKeys(Keys.ENTER);

        WebElement ramCheckbox = getDriver().findElement(By.xpath("//input[contains(@aria-label, '32GB')]"));
        ramCheckbox.sendKeys(Keys.ENTER);
        ramCheckbox.click();

        WebElement results = getDriver().findElement(By.id("ps-wrapper"));
        List<WebElement> children = results.findElements(By.xpath("./child::*"));
        String price = children.get(0).findElement(By.className("ps-dell-price")).getText();

        Assert.assertEquals(price, EXPECTED_PRICE);
    }
}
