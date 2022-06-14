import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class LogoConsistentStylingTest extends BaseTest {

    @Test
    public void testHeaderLogoIsImage() {
        Assert.assertEquals(getDriver().findElement(By.id("jenkins-head-icon")).getTagName(), "img");
    }

    @Test
    public void testHeaderLogoImageExtensionIsSvg() {
        Assert.assertTrue(getDriver().findElement(By.id("jenkins-head-icon")).getAttribute("src").contains(".svg"));
    }
}
