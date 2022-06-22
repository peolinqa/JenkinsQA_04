import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Assert.assertEquals(amountLinks("Mavenized dependencies"), 107);
    }

    @Test
    public void testAmountLinksStaticResources() {
        Assert.assertEquals(amountLinks(STATIC_RESOURCES), 4);
    }

    @Test
    public void testLinkAntLRParserGenerator() {
        enterAboutJenkins();

        final Set<String> expectedSet = Set.of(getDriver().getTitle(), "ANTLR");

        getDriver().findElement(By.xpath("//a[text()='AntLR Parser Generator']")).click();

        Set<String> actualSet = new HashSet<>();
        for (String handle : getDriver().getWindowHandles()) {
            actualSet.add(getDriver().switchTo().window(handle).getTitle());
        }

        Assert.assertEquals(actualSet, expectedSet);
    }

}
