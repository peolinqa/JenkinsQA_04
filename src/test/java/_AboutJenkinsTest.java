import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _AboutJenkinsTest extends BaseTest {
    private void enterAboutJenkins() {
        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.AboutJenkins.click(getDriver());
    }

    private int amountLinks(String text) {
        enterAboutJenkins();

        List<WebElement> columnName = getDriver().findElements(
                By.xpath(String.format("//h2[text()='%s']//parent::div//tbody/tr", text)));

        return columnName.size();
    }

    @Test
    public void testAmountLinksMavenizedDependencies() {
        Assert.assertEquals(amountLinks("Mavenized dependencies"), 107);
    }

    @Test
    public void testAmountLinksStaticResources() {
        Assert.assertEquals(amountLinks("Static resources"), 4);
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
