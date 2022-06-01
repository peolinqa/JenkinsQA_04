package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import runner.BaseTest;

@Ignore
public class GroupWestSideTest extends BaseTest {

    private static final String URL = "https://demoqa.com/widgets";

    private static final By BY_MAIN_HEADER = By.cssSelector(".main-header");
    private static final By BY_PROGRESS_BAR = By.cssSelector("#progressBar [role=progressbar]");
    private static final By BY_START_STOP_BUTTON = By.cssSelector("#startStopButton");

    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    private void clickElement(By selector) {
        WebElement element = getDriver().findElement(selector);
        scroll(getDriver(), element);
        element.click();
    }

    @Test
    public void testValeriyKanProgressBarStartStop() throws InterruptedException {

        getDriver().get(URL);

        clickElement(By.xpath("//span[text()='Progress Bar']"));

        Assert.assertEquals(getDriver().findElement(BY_MAIN_HEADER).getText(), "Progress Bar");

        clickElement(BY_START_STOP_BUTTON);
        Thread.sleep(100);
        clickElement(BY_START_STOP_BUTTON);

        Assert.assertNotEquals(getDriver().findElement(BY_PROGRESS_BAR).getText(), "0%");
    }

    @Ignore
    @Test
    public void testValeriyKanSlideInputRangeControlToZero() {

        getDriver().get(URL);

        clickElement(By.xpath("//span[text()='Slider']"));

        Assert.assertEquals(getDriver().findElement(BY_MAIN_HEADER).getText(), "Slider");

        WebElement slider = getDriver().findElement(By.xpath("//input[@type='range']"));

        Actions actions = new Actions(getDriver());
        actions.moveToElement(slider).build().perform();

        int widthOfElement = slider.getRect().getWidth();
        int desiredRight100Percents = widthOfElement/2;
        int desiredLeft0Percents = (-1) * widthOfElement / 2;

        actions.moveByOffset(desiredRight100Percents, 0).click().build().perform();

        Assert.assertEquals(slider.getAttribute("value"), "100");
    }
}
