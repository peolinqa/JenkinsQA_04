import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import runner.BaseTest;

public class CreateNewViewWithExistingNameTest extends BaseTest {
    final String NAME_OF_VIEW = "My new view";
    @BeforeMethod
    public void createNewView() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();
    }
    @Test
    public void testCreateNewViewWithAnExistingName() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();

        String errorMessageActual = getDriver().findElement(By.className("error")).getText();
        String errorMessageExpected = "A view already exists with the name " + '"' + NAME_OF_VIEW + '"';

        Assert.assertEquals(errorMessageActual, errorMessageExpected);
    }
    @AfterMethod
    public void deleteCreatedView() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='/view/My%20new%20view/']")).click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }
}
