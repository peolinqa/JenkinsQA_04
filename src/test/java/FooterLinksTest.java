import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Set;

public class FooterLinksTest extends BaseTest {

    @Ignore
    @Test
    public void linkRestApiTest() {
        getDriver().findElement(By.xpath("//div[@class = 'page-footer__links rest_api hidden-xs']")).click();
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/api/");
    }

    @Test
    public void linkJenkinsVersionTest() {
        getDriver().findElement(By.xpath("//a[contains(text(),'Jenkins')]")).click();

        Set<String> handles = getDriver().getWindowHandles();
        String current = getDriver().getWindowHandle();
        handles.remove(current);
        String newTab = handles.iterator().next();
        getDriver().switchTo().window(newTab);

        Assert.assertEquals(getDriver().getCurrentUrl(),"https://www.jenkins.io/");
        getDriver().switchTo().window(current);

    }

}
