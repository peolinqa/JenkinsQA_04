import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateMultiConfigurationProject_US_041_Test extends BaseTest {

    private final String PROJECT_NAME = "Mcproject";

    private void createMCProject(String name) {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
    }

    private void deleteMCProject() {
        getDriver().findElement(By.linkText("Delete Multi-configuration project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void TC_041_006_CreateMultiConfigurationProjectTest() {
        String expectedResult = "Mcproject";

        createMCProject(PROJECT_NAME);

        String actualResult = getDriver().findElement(By.xpath("//div/ul/li/a[@href='/job/" + PROJECT_NAME +"/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteMCProject();
    }

    @Test
    public void TC_042_002_AddDescriptionTest(){
        String expectedResult = "test";
        createMCProject(PROJECT_NAME);

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//div/textarea[@name=\"description\"]")).sendKeys("test");
        getDriver().findElement(By.id("yui-gen2-button")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteMCProject();
    }

    @Test
    public void TC_043_004_RenameMCProjectTest() {
        String expectedResult = "Project McprojectRename";

        createMCProject(PROJECT_NAME);
        getDriver().findElement(By.linkText("Rename")).click();
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div/div/div[2]/input"))
                .sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "McprojectRename");
        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteMCProject();
    }

    @Test
    public void TC_043_005_RenameMCProjectErrorTest() {
        String expectedResult = "Error\nThe new name is the same as the current name.";

        createMCProject(PROJECT_NAME);
        getDriver().findElement(By.linkText("Rename")).click();
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div/div/div[2]/input"));
        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        getDriver().findElement(By.xpath("//div/ul/li/a[@href='/job/Mcproject/']")).click();
        deleteMCProject();
    }
}
