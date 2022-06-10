import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NewItemPageTest extends BaseTest {
    final String PROJECT_NAME = "new_project";

    public void clickNewItem() {
        getDriver().findElement(By.xpath("//div[@class='task '][1]")).click();
    }

    @Test
    public void testFormSubmissionHappyPath(){

        clickNewItem();

        WebElement ok_btn = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        if (ok_btn.getAttribute("class").contains("disabled")) {
            getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
            getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
            boolean elementIsFound;
            try {
                getDriver().findElement(By.xpath("//li[@aria-checked='true']"));
                elementIsFound = true;
            } catch (NoSuchElementException ex) {
                elementIsFound = false;
            }
            Assert.assertTrue(elementIsFound);
            getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
            try {
                getDriver().findElement(By.xpath("//li[@aria-checked='true']"));
            } catch (NoSuchElementException ex) {
                elementIsFound = false;
            }
            Assert.assertTrue(elementIsFound);
            ok_btn.click();
            WebElement nameItem = getDriver().findElement(By.xpath("//li[@class='item'][2]/a"));
            Assert.assertEquals(nameItem.getText(), PROJECT_NAME);
        }
    }
}

