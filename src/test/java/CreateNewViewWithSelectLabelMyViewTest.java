import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateNewViewWithSelectLabelMyViewTest extends BaseTest {
    final String NAME_OF_VIEW = "My new view";

    @Test
    public void testCreateNewView() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();

        Assert.assertEquals(
                NAME_OF_VIEW,
                getDriver()
                        .findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '/view')]"))
                        .getText()
        );
    }

    @AfterMethod
    public void deleteCreatedView() {
        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver().findElement(
                        By.xpath("//div[@id='projectstatus-tabBar']//a[contains(@href, '/view')]"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1")).click();
    }
}
