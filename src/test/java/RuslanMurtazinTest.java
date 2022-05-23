import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RuslanMurtazinTest extends BaseTest {

    private static final String URL = "http://the-internet.herokuapp.com";

    @Test
    public void testRuslanMurtazinHorizontalSlider() {
        getDriver().get(URL);

        WebElement horizontalSlider = getDriver().findElement(By.xpath("//ul/li[24]/a"));
        horizontalSlider.click();

        WebElement buttonHorizontalSlider = getDriver().findElement(By.xpath("//div/div/input"));

        Actions actions = new Actions(getDriver());
        actions.moveToElement(buttonHorizontalSlider).clickAndHold(buttonHorizontalSlider)
                .moveByOffset(30, 0).release().build().perform();

        Assert.assertEquals(getDriver().findElement(By.xpath(
                "//span[text()='4']")).getText(), "4");
    }

    @Test
    public void testRuslanMurtazinDropdownList() {
        getDriver().get(URL);

        getDriver().findElement(By.linkText("Dropdown")).click();
        getDriver().findElement(By.id("dropdown")).click();
        getDriver().findElement(By.xpath("//select/option[3]")).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//select/option[3]")).getText(), "Option 2");
    }

    @Test
    public void testRuslanMurtazinInputs() {
        getDriver().get(URL);

        getDriver().findElement(By.cssSelector("li:nth-child(27)>a")).click();
        WebElement typeNumber = getDriver().findElement(By.cssSelector("input[type=number]"));
        typeNumber.sendKeys("123456");

        Assert.assertEquals(typeNumber.getAttribute("value"), "123456");
    }
}
