package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class KsuTitova99BottlesOfBeerHW12Tests extends BaseTest {

    /**
     * TC_12_01 Подтвердите, что в меню BROWSE LANGUAGES, подменю  J,
     * пользователь может найти описание страницы, на которой перечеслены все программные языки,
     * начинающиеся с буквы J,  отсортированные по названию
     * Шаги:
     * Открыть базовую страницу
     * Нажать на пункт меню BROWSE LANGUAGES
     * Нажать на подменю J
     * Подтвердить, что пользователь видит текст
     * “All languages starting with the letter J are shown, sorted by Language.”
     */


    @Test
    public void testKseniyaTitovaBROWSELANGUAGESsubmenuJ() {
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement menuBrowseLanguages = getDriver().findElement(By.xpath("//ul/li/a[@href='/abc.html']"));
        menuBrowseLanguages.click();

        WebElement subtitleJ = getDriver().findElement(By.xpath("//ul/li/a[@href='j.html']"));
        subtitleJ.click();

        WebElement result = getDriver().findElement(By.xpath("//div[@id='main']/p"));

        Assert.assertEquals(result.getText(), expectedResult);
    }

}
