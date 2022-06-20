import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Iterator;
import java.util.List;

public class _AboutJenkinsTest extends BaseTest {
    private static final String MAVENIZED_DEPENDECIES = "Mavenized dependencies";
    private static final String STATIC_RESOURCES = "Static resources";

    private void enterAboutJenkins() {
        getDriver().findElement(By.linkText("Manage Jenkins")).click();
        getDriver().findElement(By.xpath("//dt[text()='About Jenkins']")).click();
    }

    private int amountLinks(String text) {
        enterAboutJenkins();

        String locator = String.format("//h2[text()='%s']//parent::div//tbody/tr", text);

        List<WebElement> columnName = getDriver().findElements(
                By.xpath(locator));

        return columnName.size();
    }

    @Test
    public void testAmountLinksMavenizedDependencies() {
        Assert.assertEquals(amountLinks(MAVENIZED_DEPENDECIES), 107);
    }

    @Test
    public void testAmountLinksStaticResources() {
        Assert.assertEquals(amountLinks(STATIC_RESOURCES), 4);
    }

    @Test
    public void testLinkAntLRParserGenerator() {
        enterAboutJenkins();
        getDriver().findElement(By.xpath("//a[text()='AntLR Parser Generator']")).click();

        String aboutJenkins = getDriver().getWindowHandle();
        String actualResult = "";

        Iterator<String> windowStrings = getDriver().getWindowHandles().iterator();

        while (windowStrings.hasNext()) {

            String secondWindow = windowStrings.next();

            if (!aboutJenkins.equals(secondWindow)) {
                getDriver().switchTo().window(secondWindow);

                actualResult = getDriver().switchTo().window(secondWindow).getTitle();
            }
        }

        Assert.assertEquals(actualResult, "ANTLR");
    }

}
