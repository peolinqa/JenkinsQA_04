import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class US_001_tests extends BaseTest {

    @Test
    public void testIconsStart(){
        Assert.assertTrue(getDriver().findElement( By.id("jenkins-head-icon")).isDisplayed());
    }


    @Test
    public void testIconsJenkinsStart(){
        Assert.assertTrue(getDriver().findElement( By.id("jenkins-name-icon")).isDisplayed());
    }

    @Test
    public void test_TC_007_ValidCharactersInTheFreestyleProject1() {
        String expectedResult = "» '@' is an unsafe character";


        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProjectTest@");
        String actualResult = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void test_TC_007_ValidCharactersInTheFreestyleProject2() {
        String expectedResult = "» ':' is an unsafe character";

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProjectTest:");
        String actualResult = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void test_TC_007_ValidCharactersInTheFreestyleProject3() {
        String expectedResult = "» '!' is an unsafe character";

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProjectTest!");
        String actualResult = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }
}
