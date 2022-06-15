import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class DeleteExistingFreestyleProjectTest extends BaseTest {

    public static final String ITEMNAME = "Valid item name" + (int) (Math.random() * 1100);
    public static final String DASHBOARD_BUTTON = "//a[text()='Dashboard']";

    @Ignore
    @Test
    public void test_TC_008_002_DeleteExistingFreestyleProject_AD_DreamTeam() {
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(ITEMNAME);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait20().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='bottom-sticker']/div[2]/span/span/button")));
        getDriver().findElement(By.xpath("//div[@id='bottom-sticker']/div[2]/span/span/button")).click();
        getWait20().until(ExpectedConditions.elementToBeClickable(By.xpath(DASHBOARD_BUTTON))).click();
        WebElement hoverable = getDriver().findElement(By.xpath("//*[@id='job_" + ITEMNAME + "']"+"/td[3]/a"));
        new Actions(getDriver())
                .moveToElement(hoverable)
                .perform();
        getWait20().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='menuSelector']")));
        getDriver().findElement(By.xpath("//div[@id='menuSelector']")).click();
        getDriver().findElement(By.xpath("//div[@class='bd']//li[@index='4']/a/span")).click();
        try {
            Alert alt = getDriver().switchTo().alert();
            alt.accept();
        } catch (NoAlertPresentException noe) {
        }
        List<WebElement> elements = getDriver().findElements(By.xpath("//*[@class='jenkins-table  sortable']/tbody/tr"));
        int k=0;
        for (WebElement el:elements){
            boolean isDisplayed =  (el.getAttribute("id").contains(ITEMNAME));
            if (isDisplayed){
                k++;
            }
        }
        Assert.assertEquals(k, 0);
    }
}
