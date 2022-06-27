import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _PeopleTest extends BaseTest {

    private static final String XPATH_FOR_SIZE_CHECK = "//table[@id='people']/tbody/tr[1]/td[4]";
    private static final String NEW_USER_DESCRIPTION = TestUtils.getRandomStr();//"My new description";

    @Test
    public void checkFunctionalityIconsSMLTest() {

        getDriver().findElement(By.xpath("//div[@id='tasks']/div[2]/span/a")).click();

        getDriver().findElement(
                By.xpath("//ol/li[2]/a")).click();
        int sizeM = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK)).getSize().height;

        getDriver().findElement(
                By.xpath("//ol/li[3]/a")).click();
        int sizeL = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK)).getSize().height;

        getDriver().findElement(
                By.xpath("//ol/li[1]")).click();
        int sizeS = getDriver().findElement(
                By.xpath(XPATH_FOR_SIZE_CHECK)).getSize().height;

        Assert.assertEquals(sizeM, 40);
        Assert.assertEquals(sizeL, 50);
        Assert.assertEquals(sizeS, 34);
    }

    @Test
    public void addDescriptionForUserTest() {

        getDriver().findElement(By.xpath("//div[@id='tasks']/div[2]/span/a")).click();
        getDriver().findElement(By.xpath("//table[@id='people']/tbody/tr[1]/td[2]/a")).click();//789561562233200
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//div[@id='description']/form/div[1]/div[1]/textarea"))
                .sendKeys(NEW_USER_DESCRIPTION);
        getDriver().findElement(By.id("yui-gen1-button")).click();

        String newDescription = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"))
                .getText();

        Assert.assertEquals(newDescription, NEW_USER_DESCRIPTION);
    }
}
