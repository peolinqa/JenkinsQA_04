import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DeleteViewTest extends BaseTest {
    final String NAME_OF_VIEW = "My new view";

    @BeforeMethod
    public void createNewView() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();
    }

    @Test
    public void testDeleteCreatedView() {
        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver()
                .findElement(By.xpath("//div[@id='projectstatus-tabBar']//a[contains(@href, '/view')]"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1")).click();
    }
}
