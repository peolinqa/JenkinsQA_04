package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MaksPtHomeWork12Test extends BaseTest {

    private static final String URL = "http://www.99-bottles-of-beer.net/";

    private WebElement searchBrowseLanguages() {

        return getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"));
    }

    @Test
    public void testLanguagesTextJ() {
        getDriver().get(URL);

        searchBrowseLanguages().click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p[text()]")).getText(),
                "All languages starting with the letter J are shown, sorted by Language.");
    }

    @Test
    public void testLanguagesMLastMySQL() {
        getDriver().get(URL);

        searchBrowseLanguages().click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='language-mysql-1252.html']")).getText(),
                "MySQL");
    }
}