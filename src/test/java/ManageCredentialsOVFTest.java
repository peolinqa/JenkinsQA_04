import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.Arrays;

public class ManageCredentialsOVFTest extends BaseTest {
    @Test
    public void testIconSizeChangePositive() {
        String[] expectedResult = new String[] {"icon-credentials-system-store icon-sm",
                "icon-credentials-system-store icon-md", "icon-credentials-system-store icon-lg"};
        String[] actualResult = new String[3];

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//dt[contains(text(),'Manage Credentials')]")).click();

        getDriver().findElement(By.xpath("//a[@href='/iconSize?16x16']")).click();
        actualResult[0] = getDriver().findElement(
                By.xpath(".//td[@data='Jenkins Credentials Provider']//img")).getAttribute("class");
        getDriver().findElement(By.xpath("//a[@href='/iconSize?24x24']")).click();
        actualResult[1] = getDriver().findElement(
                By.xpath(".//td[@data='Jenkins Credentials Provider']//img")).getAttribute("class");
        getDriver().findElement(By.xpath("//a[@href='/iconSize?32x32']")).click();
        actualResult[2] = getDriver().findElement(
                By.xpath(".//td[@data='Jenkins Credentials Provider']//img")).getAttribute("class");

        Assert.assertEquals(expectedResult, actualResult);
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
    public void testIconSizeCSSChangePositive() {
        String[] buttonSPressed = new String[] {"rgba(248, 248, 248, 1)", "rgba(0, 0, 0, 0)", "rgba(0, 0, 0, 0)"};
        String[] buttonMPressed = new String[] {"rgba(0, 0, 0, 0)", "rgba(248, 248, 248, 1)", "rgba(0, 0, 0, 0)"};
        String[] buttonLPressed = new String[] {"rgba(0, 0, 0, 0)", "rgba(0, 0, 0, 0)", "rgba(248, 248, 248, 1)"};
        String[] currentButtonResult = new String[3];
        boolean[] expectedResult = new boolean[] {true, true, true};
        boolean[] actualResult = new boolean[] {false, false, false};

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//dt[contains(text(),'Manage Credentials')]")).click();

        getDriver().findElement(By.xpath("//a[@href='/iconSize?16x16']")).click();
        currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonSPressed)) {
            actualResult[0] = true;
        }

        getDriver().findElement(By.xpath("//a[@href='/iconSize?24x24']")).click();
        currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonMPressed)) {
            actualResult[1] = true;
        }

        getDriver().findElement(By.xpath("//a[@href='/iconSize?32x32']")).click();
        currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonLPressed)) {
            actualResult[2] = true;
        }

        Assert.assertEquals(expectedResult, actualResult);
    }
}
