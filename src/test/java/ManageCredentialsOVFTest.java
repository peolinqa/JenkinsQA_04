import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.Arrays;

public class ManageCredentialsOVFTest extends BaseTest {

    private final By SMALL_SIZE_ICONS = By.xpath("//a[@href='/iconSize?16x16']");
    private final By MEDIUM_SIZE_ICONS = By.xpath("//a[@href='/iconSize?24x24']");
    private final By LARGE_SIZE_ICONS = By.xpath("//a[@href='/iconSize?32x32']");

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
        String[] currentButtonResult = new String[3];
        boolean[] expectedResult = new boolean[] {true, true, true};
        boolean[] actualResult = new boolean[] {false, false, false};

        goToManageCredentials();

        getDriver().findElement(SMALL_SIZE_ICONS).click();
        currentButtonResult = elementBGColor();
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
