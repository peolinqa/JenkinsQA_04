import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DreamTeamJavaGroupTest extends BaseTest {

    public static final String FOOTER_REST_API = "//a[@href='api/']";
    public static final String REST_API_PAGE_URL = "http://localhost:8080/api/";
    public static final String XPATH_FOR_SIZE_CHECK = "//*[@id='person-admin']/td[4]";
    public static final String ITEMNAME = "Valid item name" + (int) (Math.random() * 1000);
    public static final String CREATE_PROJECT_SAVE_BUTTON = "//button[@type='submit']";
    public static final String DASHBOARD_BUTTON = "//a[text()='Dashboard']";

    @Test
    public void testTC_132_001_FooterCheckLinksFelix_IX() {
        getDriver().findElement(By.xpath(FOOTER_REST_API)).click();
        Assert.assertEquals(getDriver().getCurrentUrl(), REST_API_PAGE_URL);
    }

    @Test
    public void test_TC_001_037_CreateNewFreestyleProject_AD() {
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(ITEMNAME);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait20().until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_PROJECT_SAVE_BUTTON)));
        getDriver().findElement(By.xpath(CREATE_PROJECT_SAVE_BUTTON)).click();
        getWait20().until(ExpectedConditions.elementToBeClickable(By.xpath(DASHBOARD_BUTTON)));
        getDriver().findElement(By.xpath(DASHBOARD_BUTTON)).click();
        String temp = getDriver().findElement(By.id("job_" + ITEMNAME)).getText();
        Assert.assertTrue(temp.contains(ITEMNAME));
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
        WebElement sizeM = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK));
        Assert.assertEquals(sizeM.getSize().height, 40);

        getDriver().findElement(
                By.xpath("//*[@id='main-panel']/div[2]/div[1]/ol/li[3]/a")).click();
        WebElement sizeL = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK));
        Assert.assertEquals(sizeL.getSize().height, 50);

        getDriver().findElement(
                By.xpath("//*[@id='main-panel']/div[2]/div[1]/ol/li[1]")).click();
        WebElement sizeS = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK));
        Assert.assertEquals(sizeS.getSize().height, 34);
    }


}
