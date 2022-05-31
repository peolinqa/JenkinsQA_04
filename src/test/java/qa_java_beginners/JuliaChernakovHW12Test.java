package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class JuliaChernakovHW12Test extends BaseTest {
    /**
     * Подтвердите, что в меню BROWSE LANGUAGES, подменю  J, пользователь
     * может найти описание страницы, на которой перечеслены все программные языки,
     * начинающиеся с буквы J, отсортированные по названию
     */
    @Test
    public void testPageDescription() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net");
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        WebElement text = getDriver().findElement(By.xpath("//p[strong]"));
        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}

