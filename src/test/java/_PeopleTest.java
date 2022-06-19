import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _PeopleTest extends BaseTest {

    public static final String XPATH_FOR_SIZE_CHECK = "//*[@id='person-admin']/td[4]";

    @Test
    public void checkFunctionalityIconsSMLTest() {

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
