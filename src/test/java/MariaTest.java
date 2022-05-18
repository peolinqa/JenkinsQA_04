import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class MariaTest extends BaseTest {

    @Test
    public void mariaVerbitskayaTest() {
        getDriver().get("https://www.duolingo.com/");

        Assert.assertEquals(getDriver().getTitle(), "Duolingo - The world's best way to learn a language");

        WebElement startBox = getDriver().findElement(By.className("liLLN"));
        startBox.click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("._1Ii2h")).getText(), "I want to learn...");

        WebElement chooseLanguage = getDriver().findElement(By.cssSelector("[data-test='language-card language-de']"));
        chooseLanguage.click();

        WebElement question = getDriver().findElement(By.cssSelector("[data-test='acquisitionSurvey'] > h1"));

        Assert.assertEquals(question.getText(), "How did you hear about us?");
    }
}
