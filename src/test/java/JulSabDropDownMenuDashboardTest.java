import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JulSabDropDownMenuDashboardTest extends BaseTest {
    private static final By XPATH_DISAPPEARING_BUTTON = By.xpath("//div[@id='menuSelector']");
    private static final By XPATH_DROP_DOWN_MENU = By.xpath("//ul[@class='first-of-type']/li");
    private static final By XPATH_DASHBOARD = By.xpath("//a[contains(text(), 'Dashboard')]");

    @Test
    public void testCommonCheckDropDownMenu105001() {
        List<String> expectedItems = List.of("New Item", "People", "Build History", "Manage Jenkins", "My Views", "Lockable Resources", "New View");
        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver()
                .findElement(XPATH_DASHBOARD)).build().perform();
        action.moveToElement(getDriver()
                .findElement(XPATH_DISAPPEARING_BUTTON)).click().build().perform();

        List<String> actualItems = getDriver().findElements(XPATH_DROP_DOWN_MENU).stream()
                .filter(s -> !s.getText().isEmpty())
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertEquals(actualItems, expectedItems);
    }
}
