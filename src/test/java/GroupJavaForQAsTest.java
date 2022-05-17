import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class GroupJavaForQAsTest extends BaseTest {

    private void openSeleniumPage() {
        getDriver().get("https://www.selenium.dev/");
    }

    @Test

    public void testCheckDocumentationButton() throws InterruptedException {
        openSeleniumPage();
        WebElement readMoreButton = getDriver().findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[4]/a/span"));
        Thread.sleep(500);
        readMoreButton.click();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.selenium.dev/documentation/");
    }

    @Test
    public void testCountOfDropDownSectionButtons(){
        openSeleniumPage();

        List<WebElement> DropDownButtons = getDriver().findElements(By.id("navbarDropdown"));
        Assert.assertEquals(DropDownButtons.size(), 2);
    }



}
