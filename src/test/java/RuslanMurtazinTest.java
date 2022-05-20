import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RuslanMurtazinTest extends BaseTest {

    @Test
    public void testRuslanMurtazinHorizontalSlider() {

        getDriver().get("http://the-internet.herokuapp.com");

        WebElement horizontalSlider = getDriver().findElement(By.xpath("//ul/li[24]/a"));
        horizontalSlider.click();

        WebElement buttonHorizontalSlider = getDriver().findElement(By.xpath("//div/div/input"));

        Actions actions = new Actions(getDriver());
        actions.moveToElement(buttonHorizontalSlider).clickAndHold(buttonHorizontalSlider).moveByOffset(
                30, 0).build().perform();
    }
}