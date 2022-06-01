package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

@Ignore
public class MaratTest extends BaseTest {

    private static final String URL = "https://www.wikipedia.org/";

    private void initiateSearch(String request) {
        getDriver().findElement(By.id("searchInput")).sendKeys(request);
        getDriver().findElement(By.xpath("//i[@class='sprite svg-search-icon']")).submit();
    }

    @Test
    public void testLanguageChange() {
        getDriver().get(URL);

        Select languageSelector = new Select(getDriver().findElement(By.id("searchLanguage")));
        languageSelector.selectByVisibleText("Italiano");

        WebElement languageIcon = getDriver().findElement(By.id("jsLangLabel"));
        Assert.assertEquals(languageIcon.getText(), "IT");
    }

    @Test
    public void testSearchResult() {
        getDriver().get(URL);
        initiateSearch("Test automation");

        WebElement testAutomationHeader = getDriver().findElement(By.id("firstHeading"));
        Assert.assertEquals(testAutomationHeader.getText(), "Test automation");
    }

    @Test
    public void testTableOfContents() {
        getDriver().get(URL);
        initiateSearch("Test automation");

        List<WebElement> contents = getDriver().findElements(
                By.xpath("//li[contains(@class,'toclevel-1')]"));
        Assert.assertEquals(contents.size(), 10);

        WebElement contentsItem = getDriver().findElement(
                By.xpath("//a[contains(@href,'Framework')]/span[@class='toctext']"));
        Assert.assertEquals(contentsItem.getText(), "Framework approach in automation");
    }

    @Test
    public void testWikiMainPage() {
        getDriver().get(URL);
        initiateSearch("Manual testing");

        getDriver().findElement(By.className("mw-wiki-logo")).click();
        Assert.assertEquals(getDriver().findElement(By.id("Welcome_to_Wikipedia")).getText(),
                "Welcome to Wikipedia");
    }
}

