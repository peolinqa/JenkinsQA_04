import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EditViewChangeName extends BaseTest {
    @Test
    public void testEditView_TC_027_001() {
        String editNameOfView = String.valueOf((int) (Math.random() * 999));

        getDriver().findElement(By.xpath("//div[@id='projectstatus-tabBar']//a[contains(@href, '/view')]"))
                .click();
        getDriver().findElement(By.linkText("Edit View")).click();

        WebElement name = getDriver().findElement(By.xpath("//input[@name='name']"));
        name.clear();
        name.sendKeys(editNameOfView);

        getDriver().findElement(By.id("yui-gen2-button")).click();

        Assert.assertEquals(editNameOfView,
                getDriver().findElement(By.xpath("//div[@id='projectstatus-tabBar']//a[contains(@href, '"
                        + editNameOfView + "')]")).getText());
    }
}

