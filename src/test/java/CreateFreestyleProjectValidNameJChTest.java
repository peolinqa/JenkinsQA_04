import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFreestyleProjectValidNameJChTest extends BaseTest {

    private final String PROJECT_NAME = "testJuliaChValidName";

    @Test(priority = 1)
    public void testCreateFreestyleProjectValidName() {

        String expectedResult = "testJuliaChValidName";

        getDriver().findElement(
                By.xpath("//div[@id='side-panel']//span[@class='task-link-text']")).click();
        getDriver().findElement(
                        By.xpath("//div[@id='main-panel']//div[@class='add-item-name']/input"))
                .sendKeys(PROJECT_NAME);
        getDriver().findElement(
                By.xpath("//div[@id='main-panel']//div[@class='col-md-offset-2 col-md-20']" +
                        "//ul[@class='j-item-options']//li[@tabindex='0']" +
                        "//label/span[(contains(text(),'Freestyle project'))]")).click();
        getDriver().findElement(
                By.xpath("//div[@id='main-panel']/div//form[@method='post']" +
                        "//div[@class='btn-decorator']/span")).click();
        getDriver().findElement(
                        By.xpath("//div[@id='bottom-sticker']/div[@class='bottom-sticker-inner']" +
                                "/span[1]"))
                .click();
        getDriver().findElement(
                By.xpath("//div[@id='tasks']/div[@class='task ']//a[@href='/']" +
                        "/span[@class='task-link-text']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//table[@id='projectstatus']//tr[@id='job_testJuliaChValidName']//" +
                        "td[3]/a")).getText(), expectedResult);
    }

    @Test(priority = 2)
    public void deleteProject() {
        getDriver().findElement(
                By.xpath("//table[@id='projectstatus']//tr[@id='job_testJuliaChValidName']//" +
                        "td[3]/a")).click();
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@data-message]/span[2]")).click();
        getDriver().switchTo().alert().accept();
    }
}

