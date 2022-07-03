import model.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _PeopleTest extends BaseTest {

    private static final String NEW_USER_DESCRIPTION = TestUtils.getRandomStr();//"My new description";

    @Test
    public void checkFunctionalityIconsSMLTest() {

        int sizeM = new HomePage(getDriver())
                .clickPeople()
                .setSizeM()
                .heightLastCommitActivityFirstCell();

        int sizeL = new HomePage(getDriver())
                .clickPeople()
                .setSizeL()
                .heightLastCommitActivityFirstCell();

        int sizeS = new HomePage(getDriver())
                .clickPeople()
                .setSizeS()
                .heightLastCommitActivityFirstCell();

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
