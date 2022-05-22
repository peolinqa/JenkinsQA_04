import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KarinaKTest extends BaseTest {

    private static String URL = ("http://automationpractice.com/index.php");

    @Test
    public void SearchDress() {

        getDriver().get(URL);

        WebElement searchDressOnPage = getDriver().findElement(By.name("search_query"));
        searchDressOnPage.sendKeys("dress");
        WebElement clickOnButton = getDriver().findElement(By.name("submit_search"));
        clickOnButton.click();
        WebElement countOfFoundElementsOnPage = getDriver().findElement(By.className("heading-counter"));
        countOfFoundElementsOnPage.getText();

        String actualResult2 = countOfFoundElementsOnPage.getText();
        String expectedResult2 = "7 results have been found.";
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void searchTABWomenOnMainPage() {

        getDriver().get(URL);

        WebElement buttonDress = getDriver().findElement
                (By.xpath("//div[@id='block_top_menu']//a[@href='http://automationpractice.com/index.php?id_category=3&controller=category']"));

        String expectedResult = "WOMEN";
        String actualResult = buttonDress.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
}