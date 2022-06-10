import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class PipelineBuildNowOVFTest extends BaseTest {
    private String createRandomName() {
        String nameSubstrate = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        return RandomStringUtils.random(7, nameSubstrate);
    }

    private void createNewPipeline(String pipelineName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(pipelineName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']"))
                .sendKeys("Test pipeline");
        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
    }

    private void deleteCreatedPipeline(String pipelineName) {
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                By.xpath("//a[@href='job/" + pipelineName + "/']"))).build().perform();
        action.moveToElement(getDriver().findElement(
                By.xpath("//div[@id='menuSelector']"))).click().build().perform();
        action.moveToElement(getDriver().findElement(
                By.xpath("//a/span[contains(text(),'Delete Pipeline')]"))).click().build().perform();
        getDriver().switchTo().alert().accept();
    }

    private void goToPipelinePage(String pipelineName) {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[@href='/']")).click();
        getDriver().findElement(By.xpath("//a[@href='job/" + pipelineName + "/']")).click();
    }

    @Test
    public void testPipelineBuildNow() {
        String[] buildSuccessfulPermalinks = new String[]{"Last build", "Last stable build", "Last successful build",
                "Last completed build"};
        String[] expectedBuildNumbers = new String[] {"#3", "#2", "#1"};

        String pipelineName = createRandomName();
        createNewPipeline(pipelineName);
        goToPipelinePage(pipelineName);

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        for (int i = 0; i < 3; i++) {
            getDriver().findElement(By.xpath("//a[@href='/job/" + pipelineName + "/build?delay=0sec']"))
                    .click();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//td[@class='build-row-cell']//a[contains(text(),'#" + (i + 1) + "')]")));
        }

        getDriver().navigate().refresh();

        List<WebElement> permalinks = getDriver().findElements(
                By.xpath("//ul[@class='permalinks-list']/li"));
        ArrayList<String> permalinksTexts = new ArrayList<>();
        for (int i = 0; i < permalinks.size(); i++) {
            permalinksTexts.add(permalinks.get(i).getText());
            Assert.assertTrue((permalinksTexts.get(i)).contains(buildSuccessfulPermalinks[i]));
        }

        List<WebElement> buildTableLinks = getDriver().findElements(
                By.xpath("//a[contains(@class,'display-name')]"));

        String[] buildNumbers = new String[3];
        for (int j = 0; j < buildTableLinks.size(); j++) {
            buildNumbers[j] = buildTableLinks.get(j).getText();
        }

        SoftAssert asserts = new SoftAssert();
        asserts.assertTrue(getDriver().findElement(
                By.xpath("//h2[normalize-space(.)='Permalinks']")).isDisplayed());
        asserts.assertTrue(getDriver().findElement(
                By.xpath("//div[contains(@class,'build-history')]")).isDisplayed());
        asserts.assertEquals(3, buildTableLinks.size());
        asserts.assertEquals(expectedBuildNumbers, buildNumbers);

        deleteCreatedPipeline(pipelineName);
    }
}