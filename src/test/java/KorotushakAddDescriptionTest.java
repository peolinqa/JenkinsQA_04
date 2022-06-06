import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KorotushakAddDescriptionTest extends BaseTest {

    private void createFolder(String name)  {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@title=\"New Item\"]")).click();

        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.className("com_cloudbees_hudson_plugins_folder_Folder")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.id("yui-gen6-button")).click();
    }

    @Test
    //add description and check it
    public void testAddNewFolderDescription(){
        WebDriver driver = getDriver();
        createFolder("Folder with description");

        driver.findElement(By.id("description-link")).click();
        String description = "Here is the description";
        driver.findElement(By.name("description")).sendKeys(description);
        driver.findElement(By.id("yui-gen1-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"description\"]/div")).getText(), description);
    }

    @Test
    //leave description textArea empty
    public void testAddEmptyFolderDescription(){
        WebDriver driver = getDriver();
        createFolder("Folder without description");

        driver.findElement(By.id("description-link")).click();
        driver.findElement(By.id("yui-gen1-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"description\"]/div")).getText(), "");
    }
}
