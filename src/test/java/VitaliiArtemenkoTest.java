import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class VitaliiArtemenkoTest extends BaseTest {
    private static final String URL = "https://www.fluke.com/en-us";
    private static final String PRODUCTS_BTN_XPATH = "//a[@href='/en-us/products' and @class='dropdown-toggle']";
    private static final String HEADER_XPATH = "//h1[@class='toc-title']";
    private static final String FEATURED_BTN_XPATH = "//a[@href='/en-us/products/featured' and @class='toc-listing-title-h3']";
    private static final String AWARDS_BNT_XPATH = "//a[@href='/en-us/products/awards' and @class='toc-listing-title-h3']";
    private static final String AWARDS_HEADER_XPATH = "//div[@class='pane-content']/h1";
    private static final String PRODUCTS_PAGE_TITLE = "View all Fluke Products | Fluke";
    private static final String PRODUCTS_PAGE_HEADER = "Fluke products: Test and measurement tools and software";
    private static final String FEATURED_PAGE_TITLE = "Featured | Fluke";
    private static final String FEATURE_PAGE_HEADER = "Featured";
    private static final String AWARDS_PAGE_TITLE = "Award winning Fluke Products | Fluke";
    private static final String AWARDS_PAGE_HEADER = "Awards";

    private void openProductsPage() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath(PRODUCTS_BTN_XPATH)).click();
    }

    private void openFeaturedPage() {
        openProductsPage();
        getDriver().findElement(By.xpath(FEATURED_BTN_XPATH)).click();
    }

    private void openAwardsPage() {
        openFeaturedPage();
        getDriver().findElement(By.xpath(AWARDS_BNT_XPATH)).click();
    }

    @Test
    public void testVitaliiArtemenkoFlukeOpenProductsPage() {
        openProductsPage();

        Assert.assertEquals(PRODUCTS_PAGE_TITLE, getDriver().getTitle());
        Assert.assertEquals(PRODUCTS_PAGE_HEADER, getDriver().findElement(By.xpath(HEADER_XPATH)).getText());
    }

    @Test
    public void testVitaliiArtemenkoFlukeOpenFeaturedPage() {
        openFeaturedPage();

        Assert.assertEquals(FEATURED_PAGE_TITLE, getDriver().getTitle());
        Assert.assertEquals(FEATURE_PAGE_HEADER, getDriver().findElement(By.xpath(HEADER_XPATH)).getText());
    }

    @Test
    public void testVitaliiArtemenkoFlukeOpenAwardsPage() {
        openAwardsPage();

        Assert.assertEquals(AWARDS_PAGE_TITLE, getDriver().getTitle());
        Assert.assertEquals(AWARDS_PAGE_HEADER, getDriver().findElement(By.xpath(AWARDS_HEADER_XPATH)).getText());
    }
}