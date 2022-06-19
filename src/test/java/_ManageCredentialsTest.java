import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.Arrays;

public class _ManageCredentialsTest extends BaseTest {
    private static final String ICON_XPATH = "//td[@data='Jenkins Credentials Provider']";
    public static final String NEW_USERNAME = "Felix";
    public static final String NEW_PASSWORD = "QA12345";
    private static final By SMALL_SIZE_ICONS = By.xpath("//a[@href='/iconSize?16x16']");
    private static final By MEDIUM_SIZE_ICONS = By.xpath("//a[@href='/iconSize?24x24']");
    private static final By LARGE_SIZE_ICONS = By.xpath("//a[@href='/iconSize?32x32']");

    private void goToManageCredentials() {
        getDriver().findElement(By.partialLinkText("Manage")).click();
        getDriver().findElement(By.partialLinkText("Manage Credentials")).click();
    }

    private String getAttributeClass() {

        return getDriver().findElement(By.xpath(".//td[@data='Jenkins Credentials Provider']//img"))
                .getAttribute("class");
    }

    public String[] elementBGColor() {
        String[] elementBGColor = new String[3];
        elementBGColor[0] = getDriver().findElement(By.xpath("//span[text()='mall']/.."))
                .getCssValue("background-color");
        elementBGColor[1] = getDriver().findElement(By.xpath("//span[text()='edium']/.."))
                .getCssValue("background-color");
        elementBGColor[2] = getDriver().findElement(By.xpath("//span[text()='arge']/.."))
                .getCssValue("background-color");

        return elementBGColor;
    }

    @Test
    public void testManageCredentials() {

        goToManageCredentials();

        String iconButtonName = getDriver().findElement(
                By.xpath("//li[@class='jenkins-icon-size__items-item']")).getText().substring(0, 1);

        String iconSizeBefore = getDriver().findElement(
                By.xpath(ICON_XPATH)).getCssValue("height");

        if (iconButtonName.equals("L")) {
            WebElement iconButtonS = getDriver().findElement(By.xpath("//a[@href='/iconSize?16x16']"));
            iconButtonS.click();
        } else {
            WebElement iconButtonL = getDriver().findElement(By.xpath("//a[@href='/iconSize?32x32']"));
            iconButtonL.click();
        }
        String iconSizeAfter = getDriver().findElement(
                By.xpath(ICON_XPATH)).getCssValue("height");

        Assert.assertNotEquals(iconSizeAfter, iconSizeBefore);
    }

    @Test
    public void testManageCredentialsChekMenu() {

        WebElement hoverable = getDriver().findElement(By.xpath("//a[@class='model-link inside inverse']"));
        new Actions(getDriver())
                .moveToElement(hoverable)
                .perform();

        getDriver().findElement(By.id("menuSelector")).click();
        getDriver().findElement(By.id("yui-gen4")).click();
        getDriver().findElement(By.xpath("//a[@title='User']")).click();
        getDriver().findElement(By.xpath("//table/tbody/tr[2]/td[2]/a")).click();
        getDriver().findElement(By.xpath("//div[2]/span/a/span[2]")).click();
        getDriver().findElement(By.xpath("//input[@name='_.username']"))
                .sendKeys(NEW_USERNAME);
        getDriver().findElement(By.xpath("//input[@name='_.password']"))
                .sendKeys(NEW_PASSWORD);
        getDriver().findElement(By.id("yui-gen1-button")).click();
        WebElement newUser = getDriver().findElement(By.xpath("//div[@id='main-panel']/table"));

        Assert.assertTrue(newUser.getText().contains(NEW_USERNAME));
    }

    @Test
    public void testIconSizeChangePositive() {

        String[] expectedResult = new String[] {"icon-credentials-system-store icon-sm",
                "icon-credentials-system-store icon-md", "icon-credentials-system-store icon-lg"};
        String[] actualResult = new String[3];

        goToManageCredentials();

        getDriver().findElement(SMALL_SIZE_ICONS).click();
        actualResult[0] = getAttributeClass();
        getDriver().findElement(MEDIUM_SIZE_ICONS).click();
        actualResult[1] = getAttributeClass();
        getDriver().findElement(LARGE_SIZE_ICONS).click();
        actualResult[2] = getAttributeClass();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIconSizeCSSChangePositive() {

        String grey = "rgba(248, 248, 248, 1)";
        String transparent = "rgba(0, 0, 0, 0)";
        String[] buttonSPressed = new String[] {grey, transparent, transparent};
        String[] buttonMPressed = new String[] {transparent, grey, transparent};
        String[] buttonLPressed = new String[] {transparent, transparent, grey};
        boolean[] expectedResult = new boolean[] {true, true, true};
        boolean[] actualResult = new boolean[] {false, false, false};

        goToManageCredentials();

        getDriver().findElement(SMALL_SIZE_ICONS).click();
        String[] currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonSPressed)) {
            actualResult[0] = true;
        }

        getDriver().findElement(MEDIUM_SIZE_ICONS).click();
        currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonMPressed)) {
            actualResult[1] = true;
        }

        getDriver().findElement(LARGE_SIZE_ICONS).click();
        currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonLPressed)) {
            actualResult[2] = true;
        }

        Assert.assertEquals(expectedResult, actualResult);
    }
}