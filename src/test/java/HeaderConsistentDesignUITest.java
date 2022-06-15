import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

import java.util.List;

public class HeaderConsistentDesignUITest extends BaseTest {

    private final By HEADER = By.id("header");

    @Test
    public void testHeaderDesignUI_TC_101_001(){
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("background-color"), "rgba(0, 0, 0, 1)");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("display"), "flex");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("height"), "56px");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("align-items"), "center");
    }

    @Test
    public void testHeaderPositionOfElementsUI_TC_101_002(){
        List<WebElement> divHeaders = getDriver().findElements(By.xpath("//header/div"));

        SoftAssert asserts = new SoftAssert();
        asserts.assertNotNull(divHeaders);
        asserts.assertEquals(divHeaders.size(), 3);
        asserts.assertEquals(divHeaders.get(0).getAttribute("class"), "page-header__brand");
        asserts.assertEquals(divHeaders.get(1).getAttribute("class"), "searchbox hidden-xs");
        asserts.assertEquals(divHeaders.get(2).getAttribute("class"), "login page-header__hyperlinks");

        List<WebElement> logoImgHeaders = getDriver().findElements(By.xpath("//a[@id='jenkins-home-link']/img"));
        asserts.assertNotNull(logoImgHeaders);
        asserts.assertEquals(logoImgHeaders.size(), 2);
        asserts.assertEquals(logoImgHeaders.get(0).getAttribute("id"), "jenkins-head-icon");
        asserts.assertEquals(logoImgHeaders.get(1).getAttribute("id"), "jenkins-name-icon");

        List<WebElement> divHeaderHyperlinks = getDriver().findElements(By.xpath(
                "//header/div[@class='login page-header__hyperlinks']/div"));
        asserts.assertNotNull(divHeaderHyperlinks);
        asserts.assertEquals(divHeaderHyperlinks.size(), 2);
        asserts.assertEquals(divHeaderHyperlinks.get(0).getAttribute("id"), "visible-am-insertion");
        asserts.assertEquals(divHeaderHyperlinks.get(1).getAttribute("id"), "visible-sec-am-insertion");

        List<WebElement> aHeaderHyperlinks = getDriver().findElements(By.xpath(
                "//header/div[@class='login page-header__hyperlinks']/a"));
        asserts.assertNotNull(aHeaderHyperlinks);
        asserts.assertEquals(aHeaderHyperlinks.size(), 2);
        asserts.assertEquals(aHeaderHyperlinks.get(0).getAttribute("href"),
                "http://localhost:8080/user/admin");
        asserts.assertEquals(aHeaderHyperlinks.get(1).getAttribute("href"),
                "http://localhost:8080/logout");

        asserts.assertAll();
    }

}
