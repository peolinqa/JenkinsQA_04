import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateNewViewWithExistingName extends BaseTest {
    @Test
    public void testCreateNewViewWithAnExistingName() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        String nameOfView = "My new view";
        getDriver().findElement(By.id("name")).sendKeys(nameOfView);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        String errorMessageActual = getDriver().findElement(
                By.xpath("//div[@class='error']")).getText();
        String errorMessageExpected = "A view already exists with the name " + '"' + nameOfView + '"' ;
        Assert.assertEquals(errorMessageActual, errorMessageExpected);
    }
}
