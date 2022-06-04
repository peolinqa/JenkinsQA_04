import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class US_001_tests extends BaseTest {

    private void createProject(){
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProjectTestKT");
        getDriver().findElement(By.className("label")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//html/body/div[5]/div/div/div/div/form/div[1]/div[12]/div/div[2]/div[2]/span[1]/span/button")
        ).click();
    }

    private void deleteProject() {
        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testIconsStart(){
        Assert.assertTrue(getDriver().findElement( By.id("jenkins-head-icon")).isDisplayed());
    }

    @Test
    public void testIconsJenkinsStart(){
        Assert.assertTrue(getDriver().findElement( By.id("jenkins-name-icon")).isDisplayed());
    }

    @Test
    public void test_TC_001_009_ValidCharactersInTheFreestyleProject1() {
        String expectedResult = "» '@' is an unsafe character";


        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProjectTest@");
        String actualResult = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void test_TC_001_009_ValidCharactersInTheFreestyleProject2() {
        String expectedResult = "» ':' is an unsafe character";

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProjectTest:");
        String actualResult = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void test_TC_001_009_ValidCharactersInTheFreestyleProject3() {
        String expectedResult = "» '!' is an unsafe character";

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProjectTest!");
        String actualResult = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }


    @Test
    public void test_TC_001_010_CreateFreestyleProject() {
        String expectedResult = "FirstProjectTestKT";
        createProject();

        String actualResult = getDriver().findElement(By.xpath("//ul/li/a[@href='/job/FirstProjectTestKT/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteProject();
    }
}
