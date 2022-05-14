import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MariaTest extends BaseTest {

    @Test
    public void TestDuolingo() {
        getDriver().get("https://www.duolingo.com/");

        Assert.assertEquals(getDriver().getTitle(), "Duolingo - Лучший в мире способ учить языки");

        WebElement startBox = getDriver().findElement(By.className("liLLN"));
        startBox.click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("._1Ii2h")).getText(), "Я хочу изучать...");
        Assert.assertEquals(getDriver().getTitle(), "Цель обучения - Duolingo");

        WebElement chooseLanguage = getDriver().findElement(By.cssSelector("[data-test='language-card language-de']"));
        chooseLanguage.click();

        WebElement question = getDriver().findElement(By.cssSelector("[data-test='acquisitionSurvey'] > h1"));

        Assert.assertEquals(question.getText(), "Как вы узнали о нас?");
    }
}
