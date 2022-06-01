package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ArailymAsarHW12_TC_12_02_Test extends BaseTest {

    @Test
    public void testLanguageMySql(){
        String expectedResult = "MySQL";
        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")
        ).click();
        getDriver().findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li/a[@href='m.html']")
        ).click();
        String actualResult = getDriver().findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='main']/table/tbody/tr/td/a[@href='language-mysql-1252.html']")
        ).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

}
