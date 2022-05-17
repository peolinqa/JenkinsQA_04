
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AndreyDTest extends BaseTest {

    @Test
    public void test(){

        String titleExpected = "Denver Public Library";

        getDriver().get("https://www.denverlibrary.org/");
        String actualTitle = getDriver().getTitle();

        Assert.assertEquals(actualTitle,titleExpected);
    }
}
