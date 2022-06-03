import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateNewViewWithSelectLabelMyView extends BaseTest {

    String nameOfView = "My new view";
    public void fillNameField() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(nameOfView);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
    }
    public boolean isNameExist() {
        fillNameField();

        boolean isExist = false;
        if (getDriver().findElement(By.xpath("//div[@class='error']")).isDisplayed()) {
            isExist = true;
        }
        return isExist;
    }
    @Test
    public void testCreateNewView_TC_026_002() {
        if (isNameExist()) {
            nameOfView += String.valueOf((int) (Math.random() * 999));
        }
        fillNameField();
        getDriver().findElement(By.xpath("//input[@name='Submit']")).click();

        Assert.assertEquals(nameOfView,
                getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '/view')]"))
                        .getText());
    }
}
