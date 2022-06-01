package qa_old;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class Vhl232Test_Test extends BaseTest {


    @Test
    public void first_test(){

        getDriver().get("http://prestashop.qatestlab.com.ua/ru/");

         new WebDriverWait(getDriver(),5)
                .until(ExpectedConditions.titleContains("http://prestashop.qatestlab.com.ua/"));

        Assert.assertEquals(getDriver().getTitle(), "http://prestashop.qatestlab.com.ua/");

    }
}
