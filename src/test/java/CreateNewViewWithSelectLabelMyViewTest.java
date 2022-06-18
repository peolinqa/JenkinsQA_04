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
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='/view/My%20new%20view/']")).click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }
}
