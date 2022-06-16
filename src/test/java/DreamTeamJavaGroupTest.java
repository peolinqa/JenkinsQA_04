import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DreamTeamJavaGroupTest extends BaseTest {

    public static final String FOOTER_REST_API = "//a[@href='api/']";
    public static final String REST_API_PAGE_URL = "http://localhost:8080/api/";
    public static final String XPATH_FOR_SIZE_CHECK = "//*[@id='person-admin']/td[4]";

    @Test
    public void testTC_132_001_FooterCheckLinksFelix_IX() {
        getDriver().findElement(By.xpath(FOOTER_REST_API)).click();
        Assert.assertEquals(getDriver().getCurrentUrl(), REST_API_PAGE_URL);
    }

    /*
     * Steps:
     *go to the menu People
     *click the icon with the size M
     *click the icon with the size L
     *click the icon with the size S
     *
     *Expected results:
     *The size is 40px
     *The size is 50px
     *The size is 34px
     * */
    @Test
    public void testTC_106_001_CheckFunctionalityIconsMariaShy() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[2]/span/a")).click();

        getDriver().findElement(
                By.xpath("//*[@id='main-panel']/div[2]/div[1]/ol/li[2]/a")).click();
        int sizeM = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK)).getSize().height;

        getDriver().findElement(
                By.xpath("//*[@id='main-panel']/div[2]/div[1]/ol/li[3]/a")).click();
        int sizeL = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK)).getSize().height;

        getDriver().findElement(
                By.xpath("//*[@id='main-panel']/div[2]/div[1]/ol/li[1]")).click();
        int sizeS = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK)).getSize().height;

        Assert.assertEquals(sizeM, 40);
        Assert.assertEquals(sizeL, 50);
        Assert.assertEquals(sizeS, 34);
    }

}
