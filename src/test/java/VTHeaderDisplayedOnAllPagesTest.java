import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class VTHeaderDisplayedOnAllPagesTest extends BaseTest {

    private static final By HEADER_XPATH = By.id("header");
    private static final String TOP_POSITION = "(0, 0)";
    private static final By HEADERS_XPATH = By.xpath("//header//*");

    @Test
    public void testIsHeaderDisplayedOnTopOnAllPages() {

        Assert.assertTrue(getDriver().findElement(HEADER_XPATH).isDisplayed());
        Assert.assertEquals(getDriver().findElement(HEADER_XPATH)
                .getLocation().toString(), TOP_POSITION);

        List<WebElement> menuItems = getDriver().findElements(
                By.xpath("//div[@class='task ']//a"));
        for (int i = 1; i <= menuItems.size(); i++) {
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();

            Assert.assertTrue(getDriver().findElement(HEADER_XPATH)
                    .isDisplayed());
            Assert.assertEquals(getDriver().findElement(HEADER_XPATH)
                    .getLocation().toString(), TOP_POSITION);

            getDriver().navigate().back();
        }
    }

    @Test
    public void testHeaderContentMatchedOrNotOnPages() {

        List<WebElement> header = getDriver().findElements(HEADERS_XPATH);
        int expectedSizeHeader = header.size();
        List<String> expectedTagsHeader = new ArrayList<>();
        List<String> expectedContentTagsHeader = new ArrayList<>();
        for (WebElement tag : header) {
            expectedTagsHeader.add(tag.getTagName());
            expectedContentTagsHeader.add(tag.getText());
        }

        List<WebElement> menuItems = getDriver().findElements(
                By.xpath("//div[@class='task ']//a"));
        for (int i = 1; i <= menuItems.size(); i++) {
            String itemName = getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).getText();
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();
            List<WebElement> headerOnMenuPage = getDriver()
                    .findElements(HEADERS_XPATH);
            int sizeHeader = headerOnMenuPage.size();
            List<String> tagsHeader = new ArrayList<>();
            List<String> contentTagsHeader = new ArrayList<>();
            for (WebElement tag : headerOnMenuPage) {
                tagsHeader.add(tag.getTagName());
                contentTagsHeader.add(tag.getText());
            }

            if (itemName.contains("Manage Jenkins")) {
                Assert.assertNotEquals(sizeHeader, expectedSizeHeader);
                Assert.assertNotEquals(tagsHeader, expectedTagsHeader);
                Assert.assertNotEquals(contentTagsHeader,
                        expectedContentTagsHeader);
            } else {
                Assert.assertEquals(sizeHeader, expectedSizeHeader);
                Assert.assertEquals(tagsHeader, expectedTagsHeader);
                Assert.assertEquals(contentTagsHeader,
                        expectedContentTagsHeader);
            }

            getDriver().navigate().back();
        }
    }
}