import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EditViewChangeNameTest extends BaseTest {
    final String NANE_OF_VIEW = "My new view";
    final String EDIT_NAME_OF_VIEW = String.valueOf((int) (Math.random() * 999));
    @BeforeMethod
    public void createNewView() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NANE_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();
    }
    @Test
    public void testEditView_TC_027_001() {
        getDriver()
                .findElement(By.xpath("//div[@id='projectstatus-tabBar']//a[contains(@href, '/view')]"))
                .click();
        getDriver().findElement(By.linkText("Edit View")).click();

        WebElement name = getDriver().findElement(By.name("name"));
        name.clear();
        name.sendKeys(EDIT_NAME_OF_VIEW);

        getDriver().findElement(By.id("yui-gen2-button")).click();

        Assert.assertEquals(
                EDIT_NAME_OF_VIEW,
                getDriver()
                        .findElement(By.xpath("//div[@id='projectstatus-tabBar']//a[contains(@href, '"
                                + EDIT_NAME_OF_VIEW + "')]"))
                        .getText()
        );
    }
    @AfterMethod
    public void deleteCreatedView() {
        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver()
                .findElement(By.xpath("//div[@id='projectstatus-tabBar']//a[contains(@href, '/view')]"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1")).click();
    }
}

