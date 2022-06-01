package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class VitaliiArtemenkoJenkins extends BaseTest {
    @Test
    public void testVitaliiArtemenkoJenkinsDocumentations() {
        getDriver().get("https://www.jenkins.io/");
        getDriver().findElement(By.xpath("//div/a[@href='/doc/']")).click();

        Assert.assertEquals("Jenkins User Documentation",
                getDriver().findElement(By.xpath("//h1")).getText());
    }
}
