import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FreestyleProjectTest extends BaseTest {
    private final String FREESTYLE_PROJECT_NAME = "NewProject";
    private final String DESCRIPTION = "NewDescription";

    @AfterMethod
    public void deleteFreestyleProject() {
        getDriver().findElement(By.xpath("//span[text() = 'Delete Project']")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testAddDescription() {
        createNewFreestyleProject();

        addDescriptionToFreestyleProject();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[text() = 'Project " + FREESTYLE_PROJECT_NAME + "']")).getText(),
                "Project " + FREESTYLE_PROJECT_NAME + "");
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[text() = '" + DESCRIPTION + "']")).getText(), DESCRIPTION);

    }

    public void createNewFreestyleProject() {
        getDriver().findElement(By.xpath("//a[@title = \"New Item\"]")).click();

        getDriver().findElement(By.xpath("//input[@id = 'name']")).
                sendKeys(FREESTYLE_PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text() = 'Freestyle project']")).click();
        getDriver().findElement(By.xpath("//button[text() = 'OK']")).click();
    }

    public void addDescriptionToFreestyleProject() {
        getDriver().findElement(By.xpath("//textarea[@name = 'description']"))
                    .sendKeys(DESCRIPTION);
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();

    }
}

