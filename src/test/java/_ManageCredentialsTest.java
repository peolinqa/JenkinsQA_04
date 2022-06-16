import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _ManageCredentialsTest extends BaseTest {
    private static final String ICON_XPATH = "//td[@data='Jenkins Credentials Provider']";

    @Test
    public void testManageCredentials() {
        getDriver().findElement(By.linkText("Manage Jenkins")).click();
        getDriver().findElement(By.xpath("//dt[contains(text(),'Manage Credentials')]")).click();

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
}