import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _ManageCredentialsTest extends BaseTest {
    private static final String ICON_XPATH = "//td[@data='Jenkins Credentials Provider']";
    public static final String NEW_USERNAME = "Felix";
    public static final String NEW_PASSWORD = "QA12345";

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
}