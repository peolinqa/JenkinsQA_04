import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AsJenkinsTest extends BaseTest {

    @Test
    public void testPractice() {
        String expectedResalt = "Includes all known “users”, including login identities which the current security realm can enumerate, " +
                "as well as people mentioned in commit messages in recorded changelogs.";

        getDriver().findElement(By.linkText("People")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//div/p[@class='jenkins-description']")).getText(), expectedResalt);
    }

    private void deleteNewView() {

        getDriver().findElement(By.linkText("Delete View")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    private String NameView = "Java View3";
    private String NewNameView = "Java New View3";

    @Test
    public void editViewVerifyChangeViewTest_TC_027_001() {

        String expectedResult = "Java New View3";

        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.linkText("New View")).click();
        getDriver().findElement(By.xpath("//input[@onchange='updateOk(this.form)']")).sendKeys(NameView);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.MyView']")).click();
        getDriver().findElement(By.xpath("//input[@name='Submit']")).click();
        getDriver().findElement(By.linkText("Edit View")).click();
        getDriver().findElement(By.xpath("//input[@autocomplete='on']")).clear();
        getDriver().findElement(By.xpath("//input[@autocomplete='on']")).sendKeys(NewNameView);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.linkText(NewNameView)).getText(), expectedResult);

        deleteNewView();
    }
}