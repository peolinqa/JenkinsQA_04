import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class MaratTest extends BaseTest {

    private void getWikiStartPage() {
        getDriver().get("https://www.wikipedia.org/");
    }

    private void initiateSearch(String request) {
        getDriver().findElement(By.id("searchInput")).sendKeys(request);
        getDriver().findElement(By.xpath("//*[@class='sprite svg-search-icon']")).submit();
    }

    @Test
    public void testStartPageTitle() {
        getWikiStartPage();
        Assert.assertEquals(getDriver().getTitle(), "Wikipedia");
    }

    @Test
    public void testLanguageChange() {
        getWikiStartPage();

        Select languageSelector = new Select(getDriver().findElement(By.id("searchLanguage")));
        languageSelector.selectByVisibleText("Italiano");

        WebElement languageIcon = getDriver().findElement(By.id("jsLangLabel"));

        Assert.assertEquals(languageIcon.getText().toLowerCase(), "it".toLowerCase());
    }

    @Test
    public void testSearchResult() {
        getWikiStartPage();
        initiateSearch("Test automation");

        WebElement testAutomationHeader = getDriver().findElement(By.id("firstHeading"));
        Assert.assertEquals(testAutomationHeader.getText(), "Test automation");

    }

    @Test
    public void testTableOfContents() {
        getWikiStartPage();
        initiateSearch("Test automation");

        List<WebElement> contents = getDriver().findElements(By.xpath("//*[@id='toc']//ul/li[contains(@class,'toclevel-1')]"));
        Assert.assertEquals(contents.size(), 10);

        WebElement contentsItem = getDriver().findElement(By
                .xpath("//*[@id='toc']//a[contains(@href,'Framework')]/span[@class='toctext']"));
        Assert.assertEquals(contentsItem.getText(), "Framework approach in automation");
    }

    @Test
    public void testWikiMainPage() {
        getWikiStartPage();
        initiateSearch("Manual testing");

        getDriver().findElement(By.className("mw-wiki-logo")).click();

        WebElement wikiHeadline = getDriver().findElement(By.id("Welcome_to_Wikipedia"));
        Assert.assertEquals(wikiHeadline.getText(), "Welcome to Wikipedia");
    }
}
