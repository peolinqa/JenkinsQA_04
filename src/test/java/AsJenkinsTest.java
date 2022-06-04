import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AsJenkinsTest  extends BaseTest {

    @Test
   public void testPractice(){
        String expectedResalt = "Includes all known “users”, including login identities which the current security realm can enumerate, " +
                "as well as people mentioned in commit messages in recorded changelogs.";

        getDriver().findElement(By.linkText("People")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//div/p[@class='jenkins-description']")).getText(),expectedResalt);
    }
}
