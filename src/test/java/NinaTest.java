import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NinaTest extends BaseTest {

    private static final String URL = "https://www.simplyrecipes.com/";
    private static final String EXPECTED_RESULT = "Sausage, Asparagus, and Mustard Strozzapreti";

    @Test
    public void testSearchRecipeDinner(){

        getDriver().get(URL);

        WebElement search = getDriver().findElement(By.id("card_1-0-3"));
        search.click();

        WebElement recipe = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(recipe.getText(), EXPECTED_RESULT);

    }
}
